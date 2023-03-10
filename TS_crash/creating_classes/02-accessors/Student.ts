class Student {

    private _firstName: string;
    private _lastName: string;

    constructor(theFirst: string, theLast: string) {
        this._firstName = theFirst;
        this._lastName = theLast;
    }

    
    public get firstName() : string {
        return this._firstName;
    }
    
    public get lastName() : string {
        return this._lastName;
    }

    
    public set firstName(v : string) {
        this._firstName = v;
    }

    
    public set lastName(v : string) {
        this._lastName = v;
    }
    
}

let myStudent = new Student("FirstName", "LastName");

console.log(myStudent.firstName + " " + myStudent.lastName);