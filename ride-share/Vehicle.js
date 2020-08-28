const { Model } = require("objection")

class Vehicle extends Model {
    static get tableName() {
        return 'Vehicle';
    }

    static get relationMappings() {
        return {
            rides: {
                relation: Model.HasManyRelation,
                join: {
                    from: 'Vehicle.id',
                    to: 'Ride.vehicleId'
                }


            },
            drivers: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Driver",
                join: {
                    from: 'Vehicle.id',
                    through: {
                        from: 'Authorization.vehicleId',
                        to: 'Authorization.driverId'
                    },
                    to: 'Driver.id'
                }
            },
            types: {
                relation: Model.BelongsToOneRelation,
                join: {
                    from: 'Vehicle.vehicleTypeId',
                    to: 'VehicleType.id'
                }
            }
        }
    }
}

module.exports = { Vehicle };


