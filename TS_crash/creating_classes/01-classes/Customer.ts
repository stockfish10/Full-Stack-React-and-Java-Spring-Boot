class Customer {


    firstName: string;
    lastName: string;

    constructor(theFirst: string, theLast: string){
        this.firstName = theFirst;
        this.lastName = theLast;
    }


}

let myCustomer = new Customer("Martin","Luther");



console.log(myCustomer.firstName + " " + myCustomer.lastName);