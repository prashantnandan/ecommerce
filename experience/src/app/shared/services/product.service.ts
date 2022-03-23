import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/observable';
import { ToasterService } from './toaster.service';
import { RestApi } from '../api/rest-api';
import { Product } from '../models/product';
@Injectable()
export class ProductService {

  api = new RestApi();

  dataChange: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);
  // Temporarily stores data from dialogs
  dialogData: any;

  constructor (private httpClient: HttpClient, private toasterService:ToasterService) {}

  get data(): Product[] {
    return this.dataChange.value;
  }

  getDialogData() {
    return this.dialogData;
  }

 /** CRUD METHODS */
 getAllProduct(): void {
  this.httpClient.get<Product[]>(this.api.PRODUCT_GET_URL).subscribe(data => {
      this.dataChange.next(data);
    },
    (error: HttpErrorResponse) => {
    console.log (error.name + ' ' + error.message);
    });
}

addProduct(product: Product): void {
  this.httpClient.post(this.api.PRODUCT_Add_URL, product).subscribe(data => {
    console.log("response is "+data);
    this.dialogData = data;
    console.log(this.dialogData);
    if(product.id!=null && product.id>0){
      this.toasterService.openSuccessSnackBar('Successfully updated','', 2000);
    }else{
      this.toasterService.openSuccessSnackBar('Successfully added','', 2000);
    }
  },
    (err: HttpErrorResponse) => {
      this.toasterService.openErrorSnackBar('Error occurred. Details: ' + err.name + ' ' + err.message,'', 8000);
    });
}

deleteProduct(id: number): void {
  console.log("product"+id);
  const url = `${this.api.PRODUCT_DELETE_URL}/${id}`;
  console.log("url=="+url);
  this.httpClient.delete(url).subscribe(data => {
      this.toasterService.openSuccessSnackBar('Successfully deleted','ok', 1000);
    },
    (err: HttpErrorResponse) => {
      this.toasterService.openErrorSnackBar('Error occurred. Details: ' + err.name + ' ' + err.message,'', 8000);
    }
  );
}

getProduct(id:number):Observable<Product>{
  console.log("product"+id);
  const url = `${this.api.PRODUCT_URL}/${id}`;
  return this.httpClient.get<Product>(url);
}


}
