const { Model } = require("objection")

class Driver extends Model{
    static get tableName(){
        return 'Driver';
    }
    static get relationMappings(){
        return {
            vehicles: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Vehicle",
                join:{
                    from: 'Driver.id',
                    through: {
                        from: 'Authorization.driverId',
                        to: 'Authorization.vehicleId'
                    },
                    to: 'Vehicle.id'
                }

            },
            rides: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Ride",
                join: {
                    from: 'Driver.id',
                    through: {
                        from: 'Drivers.driverId',
                        to: 'Drivers.rideId'
                    },
                    to: 'Ride.id'
                }
            }
        }
    }
}

module.exports = { Driver };