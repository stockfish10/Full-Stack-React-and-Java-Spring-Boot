import { Coach } from "./Coach";

export class GolfCoach implements Coach {

    getDailyWorkout(): string {
        return "Do smt that makes you better!?"
    }

}