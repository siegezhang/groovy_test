package ginq

import groovy.json.JsonSlurper

// Parse sample JSON with a list of users.
def json = new JsonSlurper().parseText('''[
{ "username": "mrhaki", "email": "mrhaki@localhost" },
{ "username": "mrhaki", "email": "user@localhost" },
{ "username": "hubert", "email": "user@localhost" },
{ "username": "hubert", "email": "hubert@localhost" },
{ "username": "hubert", "email": null }
]''')

// Helper method to return a String
// representation of the user row.
def formatUser(row) {
    row.username + "," + row.email
}

// Default ordering is ascending.
// We specify the field name we want to order on.
assert GQ {
    from user in json
    orderby user.username
    select formatUser(user)
}.toList() == ['hubert,user@localhost',
               'hubert,hubert@localhost',
               'hubert,null',
               'mrhaki,mrhaki@localhost',
               'mrhaki,user@localhost']

// We can explicitly set ordering to ascending.
assert GQ {
    from user in json
    orderby user.email in asc
    select formatUser(user)
}.toList() == ['hubert,hubert@localhost',
               'mrhaki,mrhaki@localhost',
               'mrhaki,user@localhost',
               'hubert,user@localhost',
               'hubert,null']

// By default null values are last.
// We can also make this explicit as
// option to in asc() or in desc().
assert GQ {
    from user in json
    orderby user.email in asc(nullslast)
    select formatUser(user)
}.toList() == ['hubert,hubert@localhost',
               'mrhaki,mrhaki@localhost',
               'mrhaki,user@localhost',
               'hubert,user@localhost',
               'hubert,null']

// We can combine multiple properties to sort on.
assert GQ {
    from user in json
    orderby user.username, user.email
    select formatUser(user)
}.toList() == ['hubert,hubert@localhost',
               'hubert,user@localhost',
               'hubert,null',
               'mrhaki,mrhaki@localhost',
               'mrhaki,user@localhost']

// To order descending we must specify it
// as in desc.
assert GQ {
    from user in json
    orderby user.username in desc
    select formatUser(user)
}.toList() == ['mrhaki,mrhaki@localhost',
               'mrhaki,user@localhost',
               'hubert,user@localhost',
               'hubert,hubert@localhost',
               'hubert,null']

// We can mix the ordering and set it
// differently for each property.
assert GQ {
    from user in json
    orderby user.username in asc, user.email in desc
    select formatUser(user)
}.toList() == ['hubert,user@localhost',
               'hubert,hubert@localhost',
               'hubert,null',
               'mrhaki,user@localhost',
               'mrhaki,mrhaki@localhost']

// By default all null values are last,
// but we can use nullsfirst to have null
// values as first value in the ordering.
assert GQ {
    from user in json
    orderby user.username in asc, user.email in desc(nullsfirst)
    select formatUser(user)
}.toList() == ['hubert,null',
               'hubert,user@localhost',
               'hubert,hubert@localhost',
               'mrhaki,user@localhost',
               'mrhaki,mrhaki@localhost']


def letters = GQ {
    from letter in ['a', 'b', 'c']
    select _rn, letter
}.collect { item -> [item._rn, item.letter] }

assert letters == [[0, 'a'], [1, 'b'], [2, 'c']]


def json1 = new JsonSlurper().parseText('''
[
  {
    "id": 1001,
    "language": "Groovy"
  },
  {
    "id": 1002,
    "language": "Clojure"
  },
  {
    "id": 1003,
    "language": "Java"
  }
]
''')

def languages = GQ {
    from l in json1
    where l.language == "Groovy"
    // We add 1 to _rn to make it 1-based instead of 0-based.
    // Also we use as rowNumber to give a meaningful name.
    select _rn + 1 as rowNumber, l.id as id, l.language as name
}.collect { item -> [row: item.rowNumber, name: item.name] }

// Also notice the row number is calculated based
// on the result after applying the where clause.
assert languages.first() == [row: 1, name: "Groovy"]