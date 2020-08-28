const { Model } = require("objection")

class State extends Model{
    static get tableName(){
        return 'State'
    }
    static get relationMappings(){
        return {
            locations: {
                relation: Model.HasManyRelation,
                join: {
                    from: 'State.abbreviation',
                    to: 'Location.state'
                }
            }
        }
    }
}

module.exports = { State };