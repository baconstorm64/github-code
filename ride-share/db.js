const knex = require("knex")({
  client: "pg",
  connection: {
    host: "faraday.cse.taylor.edu",
    user: "caleb_collier",
    password: "wuzateho",
    database: "caleb_collier",
  },
});

//All models
const { Model } = require("objection");
Model.knex(knex);

const { Driver } = require("./Driver");
const { Passenger } = require("./Passenger");
const { Ride } = require("./Ride");
const { Location } = require("./Location");
const { State } = require("./State");
const { Vehicle } = require("./Vehicle");
const { VehicleType } = require("./Vehicle Type");

//Initialize Hapi objects
const Joi = require("@hapi/joi");
const Hapi = require("@hapi/hapi");

//Set up server configuration
const server = Hapi.server({
  host: "localhost",
  port: 3000,
  routes: {
    cors: true,
  },
});

async function init() {
  //Log information about server
  await server.register(require("blipp"));

  await server.register({
    plugin: require("hapi-pino"),
    options: {
      prettyPrint: true,
    },
  });

  //Set up server routes
  server.route([
    //Add a vehicle to the Vehicle database
    {
      method: "POST",
      path: "/vehicles",
      config: {
        description: "Add a vehicle",
        validate: {
          payload: Joi.object({
            make: Joi.string().required(),
            model: Joi.string().required(),
            colour: Joi.string().required(),
            vehicletypeid: Joi.number().required(),
            capacity: Joi.number().required(),
            mpg: Joi.number().required(),
            licenseState: Joi.string().required(),
            licenseNumber: Joi.string().required(),
          }),
        },
      },
      handler: async (request, h) => {
        const newVehicle = await Vehicle.query().insert({
          make: request.payload.make,
          model: request.payload.model,
          colour: request.payload.colour,
          vehicletypeid: parseInt(request.payload.vehicletypeid),
          capacity: parseInt(request.payload.capacity),
          mpg: parseFloat(request.payload.mpg),
          licensestate: request.payload.licenseState,
          licensenumber: request.payload.licenseNumber,
        });

        if (newVehicle) {
          return {
            ok: true,
            msge: "Vehicle successfully added",
          };
        } else {
          return {
            ok: false,
            msge: "Vehicle could not be added",
          };
        }
      },
    },
    {
      method: "PATCH",
      path: "/vehicleupdate",
      config: {
        description: "Update a vehicle",
        validate: {
          payload: Joi.object({
            licenseNumber: Joi.string().required(),
            make: Joi.string(),
            model: Joi.string(),
            colour: Joi.string(),
            vehicletypeid: Joi.string(),
            capacity: Joi.string(),
            mpg: Joi.string(),
            licenseState: Joi.string(),
          }),
        },
      },
      handler: async (request, h) => {
        const newVehicle = await Vehicle.query().where('licensenumber', request.payload.licenseNumber).update({
          make: request.payload.make,
          model: request.payload.model,
          colour: request.payload.colour,
          vehicletypeid: parseInt(request.payload.vehicletypeid),
          capacity: parseInt(request.payload.capacity),
          mpg: parseFloat(request.payload.mpg),
          licensestate: request.payload.licenseState,
        });


        if (newVehicle) {
          return {
            ok: true,
            msge: "Vehicle successfully updated",
          };
        } else {
          return {
            ok: false,
            msge: "Vehicle could not be updated",
          };
        }
      },
    },

    {
      method: "POST",
      path: "/vehicletypes",
      config: {
        description: "Add a vehicle type",
        validate: {
          payload: Joi.object({
            type: Joi.string().required()
          }),
        },
      },
      handler: async (request, h) => {
        const newVehicleType = await VehicleType.query().insert({
          type: request.payload.type
        });

        if (newVehicleType){
          return {
            ok: true,
            msge: "New vehicle type successfully added"
          }
        }
        else{
          return {
            ok: false,
            msge: "New vehicle type could not be added"
          }
        }
      }
    },
    {
      method: "GET",
      path: "/ridereport",
      config: {
        description: "Get report of all rides"
      },
      handler: (request, h) => {
        return Ride.query(); //Also need to return all related queries involving the ride location information.
      }
    },
    {
      method: "POST",
      path: "/rideadd",
      config: {
        description: "Add a new ride"
        //Pseudocode for implementation
        /*
        payload: Joi.object({
          field1: Joi.string().required(),
          field2: Joi.string().required,
          ...
          fieldn: Joi,string().required
        });
         */
      },
      handler: async (request, h) => {
        /*
        const newRide = Ride.query().insert({
          field1: request.payload.field1,
          field2: request.payload.field2,
          ...
          fieldn: request.payload.fieldn
          });

          if (newRide){
            return {
              ok: true,
              msge: "Added ride"
            }
          }
          else{
            return {
              ok: false,
              msge: "Couldn't add ride"
            }
          }
         */
        return 0;
      }
    },
    {
      method: "POST",
      path: "/driveradd",
      config: {
        description: "Apply as a driver",
        validate: {
          payload: Joi.object({
            firstname: Joi.string().required(),
            lastname: Joi.string().required(),
            phone: Joi.string().required(),
            licensenumber: Joi.string().required()
          })
        }
      },
      handler: async (request, h) => {
        const existingDriver = await Driver.query()
            .where('licensenumber', request.payload.licensenumber)
            .first()
        if (existingDriver) {
          return {
            ok: false,
            msge: "A driver with that license already exists"
          }
        }
        const newDriver = await Driver.query().insert({
          firstname: request.payload.firstname,
          lastname: request.payload.lastname,
          phone: request.payload.phone,
          licensenumber: request.payload.licensenumber
        })

        if (newDriver) {
          return {
            ok: true,
            msge: "Sucessfully added as driver"
          }
        }
        else{
          return {
            ok: false,
            msge: "Could not add as driver"
          }
        }
      }
    },
    {
      method: "POST",
      path: "/passengeradd",
      config: {
        description: "Apply as a passenger",
        validate: {
          payload: Joi.object({
            firstname: Joi.string().required(),
            lastname: Joi.string().required(),
            phone: Joi.string().required(),
          })
        }
      },
      handler: async (request, h) => {
        const existingPassenger = await Passenger.query()
            .where('phone', request.payload.phone)
            .first()
        if (existingPassenger) {
          return {
            ok: false,
            msge: "A driver with that phone number already exists"
          }
        }
        const newPassenger = await Passenger.query().insert({
          firstname: request.payload.firstname,
          lastname: request.payload.lastname,
          phone: request.payload.phone,
        })

        if (newPassenger) {
          return {
            ok: true,
            msge: "Sucessfully added as passenger"
          }
        }
        else{
          return {
            ok: false,
            msge: "Could not add as passenger"
          }
        }
      }
    },
    {
      method: "POST",
      path: "/vehicleride",
      config: {
        description: "Assign a vehicle to a ride"
        //Pseudocode for implementation
        /*
        payload: Joi.object({
          vehicleid: Joi.string().required()
          id: Joi.string().required()
        });
         */
      },
      handler: async (request, h) => {
        /*
        const vehicleRide = Ride.query().insert({
        All related ids for both ride and vehicles
        )};

        if (vehicleRide) {
          return {
            ok: true,
            msge: "Assigned vehicle to ride"
          }
        }
        else{
          return {
            ok: false,
            msge: "Couldn't assign vehicle to ride"
          }
        }
         */
        return 0;
      }
    },
    {
      method: "POST",
      path: "/vehicleauthorize",
      config: {
        description: "Authorize a vehicle"
        /*
        payload: Joi.object({
            vehicleid: Joi.string().required()
            licensenumber: Joi.string().required()
        });
         */
      },
      handler: async (request, h) => {
        //Pseudocode for implementation
        /*
          const authorizeVehicle = await //Add to the authorization table the ID of the driver and the vehicle ID

          if (authorizeVehicle) {
            return {
              ok: true,
              msge: "Authorized"
            }
          }
          else{
            return {
              ok: false,
              msge: "Not authorized"
            }
          }
         */
        return 0;
      }
    },
    {
      method: "POST",
      path: "/rideelect",
      config: {
        description: "Elect to drive for a ride"
        /*
        payload: Joi.object({
          licensenubmer: Joi.string().required(),
          id: Joi.string().required()
        });
         */
      },
      handler: async (request, h) => {
        //Explanation of implementation
        /*
        Insert driver id and ride id into drivers table. Make sure to check that vehicle is authorized via authorization table.
         */
        return 0;
      }
    },
    {
      method: "GET",
      path: "/drivereport",
      config: {
        description: "View report of upcoming drives"
      },
      handler: async (request, h) => {
        //Explanation of implementation
        /*
        Get information from the Ride table (via Ride.query()) and print out information for a specific driver
         */
        return 0;
      }
    },
    {
      method: "POST",
      path: "/ridesignup",
      config: {
        description: "Sign up for a ride"
      },
      handler: async (request, h) => {
        //Explanation of implementation
        /*
        Add to passengers table the passenger id (via phone number) and the ride they signed up for
         */
        return 0;
      }
    },
    {
      method: "GET",
      path: "/passengerridereport",
      config: {
        description: "View report of upcoming ride for a passenger"
      },
      handler: async (request, h) => {
        //Explanation of implementation
        /*
        Get information from the Ride table (via Ride.query()) and print out information for a specific passenger
         */
        return 0;
      }
    }
  ]);

  //Actually start the sever
  await server.start();
}

//Run server start function
init();
