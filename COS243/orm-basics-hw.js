const knex = require('knex')({
    client: 'pg',
    connection: {
        host: 'faraday.cse.taylor.edu',
        user: 'readonly',
        password: 'nerds4christ',
        database: 'dvdrental'
    }
});

objection = require('objection');
const Model = objection.Model;
Model.knex(knex);

class Actor extends Model{
    static get tableName(){
        return 'actor';
    }

}

function actorsStartingWith(prefix){
    Actor.query()
        .where('first_name', 'like', `${prefix}%`)
        .orWhere('last_name', 'like', `${prefix}%`)
        .then(actors => {
            actors.forEach(actor => {
                console.log(`${actor.first_name} ${actor.last_name}`);
            })
            knex.destroy();
        })
        .catch(err => console.log(err.message));
}

actorsStartingWith("F");