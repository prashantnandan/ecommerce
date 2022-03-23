export class RestApi {
    public readonly API_URL = 'http://localhost:8083';
    //public readonly API_URL = 'http://35.182.236.2:8080/estorerest';

    //LOGIN MODULE API
    public readonly LOGIN_URL = this.API_URL + '/login';
	
	 //Policy URL
    public readonly POLICY_URL = this.API_URL + '/policy';

    public readonly POLICY_Add_URL=this.POLICY_URL+'/save';
    public readonly POLICY_GET_URL=this.POLICY_URL+'/getAll';
    public readonly POLICY_DELETE_URL=this.POLICY_URL+'/delete';

     //Product URL
     public readonly PRODUCT_URL = this.API_URL + '/product';

     public readonly PRODUCT_Add_URL=this.PRODUCT_URL+'/save';
     public readonly PRODUCT_GET_URL=this.PRODUCT_URL+'/getAll';
     public readonly PRODUCT_DELETE_URL=this.PRODUCT_URL+'/delete';

    //USER URL
    public readonly USER_URL = this.API_URL + '/users';
    
    constructor() {

    }

}
