import { Component, OnInit, ViewChild, ElementRef, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { MatChipInputEvent, MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
import { ProductService } from '../../../shared/services/product.service';
import { Product } from '../../../shared/models/product';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.scss']
})
export class AddproductComponent implements OnInit {

  product = new Product();
  title ="Add Product";

  constructor(public dialogRef: MatDialogRef<AddproductComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
            private productService:ProductService,
            public dialog: MatDialog) { }

  ngOnInit() {
    if(this.data.id!=null && this.data.id>0){
      this.product = this.data;
      this.title="Update Product";
    }
  }

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  public confirmAdd(): void {
    console.log(JSON.stringify(this.product));
    this.productService.addProduct(this.product);
  }

  getCheckMessage(){}
}
