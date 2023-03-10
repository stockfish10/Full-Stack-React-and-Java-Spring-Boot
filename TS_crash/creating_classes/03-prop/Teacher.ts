class Teacher {

    constructor(private _firstName: string, private _lastName: string) {

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

let myTeacher = new Teacher("FirstName", "LastName");

console.log(myTeacher.firstName + " " + myTeacher.lastName);