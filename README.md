# android-bloodbank

## Summary
Android application developed in Java, connected with a remote PHP/MySQL server.<br><br>
In this application users can register/login using their mobile phone, and view requests near their location, created by other users who are in need of blood donation. <br>
The user can also create their own post seeking for help, and upload it to the server, and search by donors by city.

## Database and APIs
The application uses POST and GET requests on a PHP/MySQL server (hosted on 000webhost) for the following functionalities: <br>
* login  
* register  
* fetch all requests (on main activity)  
* search for specific requests  
* create new request

## Libraries Used
#### Volley: https://gist.github.com/RISHABH3821/bc48fe91119c2efa14cfab1accc71376
#### Glide(For loading images): https://github.com/bumptech/glide
#### Fast Android Networking (For better Image Upload): https://github.com/amitshekhariitbhu/Fast-Android-Networking

## Screenshots  

<table>
  <tr>
     <td>Login Page</td>
     <td>Main Page</td>
     <td>Register Page</td>
  </tr>
   <tr>
    <td><img src="https://i.imgur.com/Naf99ww.png" width=270></td>
    <td><img src="https://i.imgur.com/9iXtPgQ.png" width=270></td>
    <td><img src="https://i.imgur.com/LlsuHNj.png" width=270></td>
  </tr>
  <tr>
     <td>Search Page</td>
     <td>Search results Page</td>
     <td>Make request Page</td>
  </tr>
  <tr>
    <td><img src="https://i.imgur.com/gJBeD9U.png" width=270></td>
    <td><img src="https://i.imgur.com/lEUnKd4.png" width=270></td>
    <td><img src="https://i.imgur.com/Bd1vDHg.png" width=270></td>
  </tr>
</table>
