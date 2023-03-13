import { Coach } from "./Coach";
import { CricketCoach } from "./CricketCoach";
import { GolfCoach } from "./GolfCoach";

let myCricketCoach = new CricketCoach();
let myGolfCoach = new GolfCoach();

let myCoachArray: Coach[] = [];

myCoachArray.push(myCricketCoach);
myCoachArray.push(myGolfCoach);

for (let coach of myCoachArray) {
    console.log(coach.getDailyWorkout());
}
