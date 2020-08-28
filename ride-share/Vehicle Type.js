const { Model } = require("objection")

class VehicleType extends Model{
    static get tableName(){
        return 'Vehicle Type'
    }
    static get relationMappings(){
        return {
            vehicles: {
                relation: Model.HasManyRelation,
                join: {
                    from: 'Vehicle Type.id',
                    to: 'Vehicle.vehicleTypeId'
                }
            }
        }
    }
}

module.exports = { VehicleType };