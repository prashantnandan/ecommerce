import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { MatDialog, MatPaginator, MatSort} from '@angular/material';
import {Observable} from 'rxjs/Observable';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {DataSource} from '@angular/cdk/collections';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { ConfirmDialogComponent } from '../dialog/confirm-dialog/confirm-dialog.component';
import { ToasterService } from '../../shared/services/toaster.service';
import { Router } from '@angular/router';
import { ProductService } from '../../shared/services/product.service';
import { Product } from '../../shared/models/product';
import { AddproductComponent } from './addproduct/addproduct.component';
import { ViewproductComponent } from './viewproduct/viewproduct.component';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  displayedColumns = ['productName','productDescription','quantity','amount','actions'];
  exampleDatabase: ProductService| null;
  dataSource: ExampleDataSource | null;

  index: number;
  id:number;
  productName: string;
  type: string;

  constructor(public httpClient: HttpClient,
    public dialog: MatDialog,
    public productService: ProductService,
    private router:Router,
    private toasterService:ToasterService) { }

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('filter') filter: ElementRef;

  ngOnInit() {
    this.loadData();
  }

  refresh() {
    this.loadData();
  }

  public loadData() {
    this.exampleDatabase = new ProductService(this.httpClient, this.toasterService);
    this.dataSource = new ExampleDataSource(this.exampleDatabase, this.paginator, this.sort);
    Observable.fromEvent(this.filter.nativeElement, 'keyup')
      .debounceTime(150)
      .distinctUntilChanged()
      .subscribe(() => {
        if (!this.dataSource) {
          return;
        }
        this.dataSource.filter = this.filter.nativeElement.value;
      });
  }

  addNew(product:Product): void {
    console.log(product);
     let dialogRef = this.dialog.open(AddproductComponent, {
       data: { product:product }
      });
 
      dialogRef.afterClosed().subscribe(result => {
       console.log('The dialog was closed'+result);
        if(result){
         this.exampleDatabase.dataChange.value.push(this.productService.getDialogData());
         this.refreshTable();
       }
      });
    }

    startEdit(i:number, id:number,productName:string, productDescription:string, quantity:string, amount:string){
      this.productName = productName;
      let dialogRef = this.dialog.open(AddproductComponent, {
        data: { id:id, productName:productName, productDescription:productDescription,quantity:quantity, amount:amount}
       });
  
       dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed'+result);
         if(result){
          // When using an edit things are little different, firstly we find record inside DataService by id
          console.log(this.productName);
          const foundIndex = this.exampleDatabase.dataChange.value.findIndex(x => x.productName === this.productName);
          console.log(foundIndex);
          // Then you update that record using data from dialogData (values you enetered)
          this.exampleDatabase.dataChange.value[foundIndex] = this.productService.getDialogData();
          // And lastly refresh table
          this.refreshTable();
        }
       });
    }

    viewProduct(product:Product){
      let dialogRef = this.dialog.open(ViewproductComponent, {
        data: { id:product.id, name:product.productName}
       });
  
       dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed'+result);
       });
    }
  

  deleteItem(i: number, id:number, name: string) {
    console.log("--------"+i+"------"+id+"-----");
    this.index = i
    this.id =id;
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: {id: id, name: name, type: "product" }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("----Result----"+result);
      if (result) {
        const foundIndex = this.exampleDatabase.dataChange.value.findIndex(x => x.id === this.id);
        // for delete we use splice in order to remove single object from DataService
        this.exampleDatabase.dataChange.value.splice(foundIndex, 1);
        this.refreshTable();
      }
    });
  }

  // If you don't need a filter or a pagination this can be simplified, you just use code from else block
  private refreshTable() {
    // if there's a paginator active we're using it for refresh
    if (this.dataSource._paginator.hasNextPage()) {
      this.dataSource._paginator.nextPage();
      this.dataSource._paginator.previousPage();
      // in case we're on last page this if will tick
    } else if (this.dataSource._paginator.hasPreviousPage()) {
      this.dataSource._paginator.previousPage();
      this.dataSource._paginator.nextPage();
      // in all other cases including active filter we do it like this
    } else {
      this.dataSource.filter = '';
      this.dataSource.filter = this.filter.nativeElement.value;
    }
  }

}

export class ExampleDataSource extends DataSource<Product> {
  _filterChange = new BehaviorSubject('');

  get filter(): string {
    return this._filterChange.value;
  }

  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  filteredData: Product[] = [];
  renderedData: Product[] = [];

  constructor(public _exampleDatabase: ProductService,
              public _paginator: MatPaginator,
              public _sort: MatSort) {
    super();
    // Reset to the first page when the user changes the filter.
    this._filterChange.subscribe(() => this._paginator.pageIndex = 0);
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Product[]> {
    // Listen for any changes in the base data, sorting, filtering, or pagination
    const displayDataChanges = [
      this._exampleDatabase.dataChange,
      this._sort.sortChange,
      this._filterChange,
      this._paginator.page
    ];

    this._exampleDatabase.getAllProduct();

    return Observable.merge(...displayDataChanges).map(() => {
      // Filter data
      this.filteredData = this._exampleDatabase.data.slice().filter((product: Product) => {
        const searchStr = (product.productName + product.productDescription).toLowerCase();
        return searchStr.indexOf(this.filter.toLowerCase()) !== -1;
      });

      // Sort filtered data
      const sortedData = this.sortData(this.filteredData.slice());

      // Grab the page's slice of the filtered sorted data.
      const startIndex = this._paginator.pageIndex * this._paginator.pageSize;
      this.renderedData = sortedData.splice(startIndex, this._paginator.pageSize);
      return this.renderedData;
    });
  }
  disconnect() {
  }



  /** Returns a sorted copy of the database data. */
  sortData(data: Product[]): Product[] {
    if (!this._sort.active || this._sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      let propertyA: number | string = '';
      let propertyB: number | string = '';

      switch (this._sort.active) {
        case 'name': [propertyA, propertyB] = [a.productName, b.productName]; break;
        case 'email': [propertyA, propertyB] = [a.productDescription, b.productDescription]; break;
      }

      const valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      const valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction === 'asc' ? 1 : -1);
    });
  }
}
