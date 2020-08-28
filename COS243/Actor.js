const { Model } = require("objection");

class Actor extends Model{
    static get tableName(){
        return 'actor';
    }
    static get relationMappings(){
        return {
            films: {
                relation: Model.ManyToManyRelation,
                modelClass: __dirname + "/Film",
                join:{
                    from: 'actor.actor_id',
                    through: {
                        from: 'film_actor.actor_id',
                        to: 'film_actor.film_id'
                    },
                    to: 'film.film_id'

                }
            }
        }
    }
}

module.exports = { Actor };