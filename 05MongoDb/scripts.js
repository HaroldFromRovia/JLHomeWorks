use new_db

db.users.insert(
  [
    {
      name: "Ivan",
      age: 16
    },
    {
      name: "Vasya",
      age: 14
    },
    {
      name: "Alex",
      age: 20
    }]
)

db.users.find(
  {
    age:{
      $gt:15
    }
  }
)
