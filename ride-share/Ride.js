const { Model } = require("objection")

class Ride extends Model{
    static get tableName(){
        return 'Ride'
    }
    static get relationMappings(){
        return {
            drivers: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Driver",
                join: {
                    from: 'Ride.id',
                    through: {
                        from: 'Drivers.rideId',
                        to: 'Drivers.driverId'
                    },
                    to: 'Driver.id'
                }
            },
            vehicles: {
                relation: Model.BelongsToOneRelation,
                join: {
                    from: 'Ride.vehicleId',
                    to: 'Vehicle.id'
                }
            },
            fromLocations: {
                relation: Model.BelongsToOneRelation,
                join: {
                    from: 'Ride.fromLocationId',
                    to: 'Location.id'
                }
            },
            toLocations: {
                relation: Model.BelongsToOneRelation,
                join: {
                    from: 'Ride.toLocationId',
                    to: 'Location.id'
                }
            },
            passengers: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Passenger",
                join: {
                    from: 'Ride.id',
                    through: {
                        from: 'Passengers.rideId',
                        to: 'Passengers.passengerId'
                    },
                    to: 'Passenger.id'
                }
            }
        }
    }
}

module.exports = { Ride };