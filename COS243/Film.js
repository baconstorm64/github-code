const { Model } = require("objection");

class Film extends Model{
    static get tableName(){
        return 'film';
    }
    static get relationMappings(){
        return {
            actors: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Actor",
                join:{
                    from: 'film.film_id',
                    through: {
                        from: 'film_actor.film_id',
                        to: 'film_actor.actor_id'
                    },
                    to: 'actor.actor_id'

                }
            }
        }
    }
}

module.exports = { Film };