const { Model } = require("objection")

class Location extends Model{
    static get tableName(){
        return 'Location';
    }
    static get relationMappings(){
        return {
            fromRides: {
                relation: Model.HasManyRelation,
                join: {
                    from: 'Location.id',
                    to: 'Ride.fromLocationId'
                }
            },
            toRides: {
                relation: Model.HasManyRelation,
                join: {
                    from: 'Location.id',
                    to: 'Ride.toLocationId'
                }

            },
            states: {
                relation: Model.BelongsToOneRelation,
                join: {
                    from: 'Location.state',
                    to: 'State.abbreviation'
                }
            }
        }
    }
}

module.exports = { Location };