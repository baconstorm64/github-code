# Drivers
* Create Passenger - POST - /driver/
{"firstName": "Randy", "lastName": "Crimbleton", "phone": "202-555-0185", "licenseNumber": 5500789

* Read all drivers - GET - /driver/
* Read a driver with certain ID - GET- /drivers/:id 

* Update Passenger - PUT - /driver/:id
{"licenseNumber": 1337420}

# Vehicle
* Create Vehicle - POST /vehicle/
{"make": "Ford", "model": "Pinto", "color": "Green", "capacity": 4, "mpg": 12, "licenseState": "IN", "licenseNumber": "YHG845"}

* Read all vehicles - GET /vehicle/
* Read a vehicle with certain ID - GET /vehicle/:id

* Update Vehicle - PUT - /vehicle/:id
{"color": "Brown"}

# Passenger
* Create Passenger - POST - /passenger/
{"firstName": Gabe, "lastName": Newell, "phone": "123-867-5309"}

* Read all passengers - GET - /passenger/
* Read a passenger with certain ID - GET /passenger/:id

* Update Passenger - PUT - /passenger/:id
{"phone": "123-420-1337"}

# Ride
* Create Ride - GET - /ride/
{"date": "05/12/1942", "time": "4:04", "distance": 54365, "fuelPrice": 0.12 "fee": 0.03}

* Read all rides - GET /ride/
* Read a ride with certain ID - GET - /ride/:id

* Update Ride - PUT - /ride/:id
{"date:" "05/12/1962"}

# Authorization
* Authorize a driver - POST - /driver/:driverId/vehicle/:vehicleId
* Deauthorize a driver - DELETE - /driver/:driverId/vehicle/:vehicleId

# Ride Assignment
* Assign driver to specific ride - POST - /driver/:driverId/ride/:rideId
* Remove driver from specific ride - DELETE - /driver/:driverId/ride/:rideId

# Sequence of Operations
1. Authorize existing driver to existing vehicle - POST - /driver/42/vehicle/17
2. Create a ride - POST - /ride/
{"date": "04/15/2020", "time": "3:08", "distance", 1, "fuelPrice": 40.32, "fee": 20}
3. Associate ride with same vehicle - POST - /vehicle/17/ride/:rideId (:rideId because we don't know the exact ID of the created ride without looking in database)

