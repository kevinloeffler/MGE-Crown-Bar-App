# MGE-Crown-Bar-App

This app was created for the mobile gui and engineering course at OST

### Inspiration
Together with a friend I run a small bar in Rapperswil called "The Crown Bar". We have talked a lot about different things we could build for us or our costumers. Stuff like, internal management systems to handle reservations or help us organise the bar better. All these projects have been webapps because I'm much more proficent with web technologies than mobile developement. But I've always had an interest in app developement and have played around with the iOS stack. With the MGE module and corresponding project I finally had a good reason to restart my mobile developement carreer :D

# App
The Crown Bar app that resulted from this course could one day be a real Crown Bar app where our costumers get important information. Currently the app has the following features:
1. Dashboard with different categories
2. Events Overview that is fetched from a REST API
3. Featured drinks with recipe
4. Link to Google Maps
5. Links to the website and instagram

### Feature List (with points)
1. Kotlin (3)
2. Functionality (2)
3. Persistency (1)?
4. Webservices (2)
5. Libraries (1)

# Interesting stuff

### Dashboard
The dashboard is the heart of the app. It's the first thing users see. It is built with constraint-layouts and therefore adaptive to differen screens.

### Events
The App is mainly intendet to display information to the user. One of the main things we need to communicate to our guests regularly are upcoming events. Those have their own activity in the app. Events are fetched from a REST API that I have built with Node and hosted on Heroku. The API returns a json object with all the events, containing the event name and event date. In the App I used Retrofit2 to make the request and the translated the response to an Event Class. This class is then handed over to a recycler view that renders it to the screen.

### Cocktails
Every few weeks we create a new cocktail and mocktail that we want our guests to know about. These drinks are prominently displayed on the dashboard and can be opened to see the recipe. Users can also favorite them. This action needs to be persistant or it is lost when the screen (activity) is closed. Therefore I've used the local storage capabilities via the 'sharedPreferences' API.
