#Movies App 

This apllication retrive data from "https://www.themoviedb.org/" by using Movie DB API.



App functions:
1. Retrive popular Movies.
2. Applying sort movies on local data by relase date , alphabitiacl and rate. 
3. Applying search on local data. 
4. Retrive data from server by filter on genre.


# Components Used:
1. Implemention a network layer using the Retrofit library.
2. Parseing the JSON response from  service into kotlin objects with the Moshi library.
3. Creaating data class from Json by using RoboPOJOGenerator plugin
4. Useing Coil to load and display that image.
5. Useing a RecyclerView to display a grid of movies.
6. Useing Intint to share movie details as text massege.
