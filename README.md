# CurrencyConverter

This app is used to show converted rate for different currencies based on the input currency as per the actual realtime rates.  
The API used for getting currency rates is https://openexchangerates.org/  
To avoid overusage of hitting API endpoints, there is a inbuilt time restriction of 30 mins between 2 successful API call.  

## How to use
1. On the launch of app, the app itself updates the rates if more than 30 mins has passed.  
2. There is a refresh button for manually updating the rates. This also follows the 30 mins restriction.  
3. On entering the amount & selecting the input currency, all the output currencies will automatically get updated in real time.  

## Android technologies implemented
1. MVVM architecture
2. Retrofit
3. View Binding
4. Material Theme
5. Dependency Injection using Hilt
6. Room DB for offline support
7. Custom GSON Converter & JsonDeserializer
8. Coroutines
9. Flow
10. MutableStates & SharedStates
11. Unit Test for Coroutine, Flow, Retrofit Response

# Demo of App

https://user-images.githubusercontent.com/36126610/184823986-14a54932-47b3-4fb3-923c-b464b22ac36d.mp4

