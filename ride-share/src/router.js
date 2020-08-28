import Vue from "vue";
import Router from "vue-router";

import Home from "./pages/Home.vue";
import Vehicles from "./pages/Vehicles.vue"
import Admin from "./pages/Admin.vue"
import VehicleType from "./pages/VehicleType.vue"
import RideAdd from "./pages/RideAdd.vue"
import Driver from "./pages/Driver.vue"
import DriverAdd from "./pages/DriverAdd.vue"
import RideReport from "./pages/RideReport.vue"
import VehicleUpdate from "./pages/VehicleUpdate.vue"
import PassengerAdd from "./pages/PassengerAdd.vue"
import Passenger from "./pages/Passenger.vue"
import RideUpdate from "./pages/RideUpdate.vue"
import VehicleRide from "./pages/VehicleRide.vue"
import VehicleRideUpdate from "./pages/VehicleRideUpdate.vue";
import VehicleAuthorize from "./pages/VehicleAuthorize.vue"
import RideElect from "./pages/RideElect.vue";
import DriveReport from "./pages/DriveReport.vue";
import RideSignUp from "./pages/RideSignUp.vue";
import PassengerRideReport from "./pages/PassengerRideReport.vue";

Vue.use(Router);

export default new Router({
	mode: "history",
	base: process.env.BASE_URL,
	routes: [
		{ name: "home-page", path: "/", component: Home },
		{ name: "vehicles", path: "/vehicles", component: Vehicles },
		{ name: "admin", path: "/admin", component: Admin},
		{ name: "vehicletypes", path: "/vehicletypes", component: VehicleType},
		{ name: "rides", path: "/rides", component: RideAdd },
		{ name: "drivers", path: "/drivers", component: Driver },
		{ name: "driveradd", path: "/driveradd", component: DriverAdd},
		{ name: "vehicleupdate", path: "/vehicleupdate", component: VehicleUpdate },
		{ name: "ridereport", path: "/ridereport", component: RideReport },
		{ name: "passengeradd", path: "/passengeradd", component: PassengerAdd },
		{ name: "passengers", path: "/passengers", component: Passenger },
		{ name: "rideupdate", path: "/rideupdate", component: RideUpdate },
		{ name: "vehicleride", path: "/vehicleride", component: VehicleRide },
		{ name: "vehiclerideupdate", path: "/vehiclerideupdate", component: VehicleRideUpdate },
		{ name: "vehicleauthorize", path: "/vehicleauthorize", component: VehicleAuthorize },
		{ name: "rideelect", path: "/rideelect", component: RideElect },
		{ name: "drivereport", path: "/drivereport", component: DriveReport },
		{ name: "ridesignup", path: "/ridesignup", component: RideSignUp },
		{ name: "passengerridereport", path: "/passengerridereport", component: PassengerRideReport }
	]

});
