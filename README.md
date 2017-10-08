CIZDAN
===============


## Building the app

Install [Android Studio](https://developer.android.com/sdk/installing/studio.html). Once it is
running, import cizdan by navigating to where you cloned or downloaded it and selecting
settings.gradle. When it is finished importing, click on the SDK Manager ![SDK Manager](https://developer.android.com/images/tools/sdk-manager-studio.png). You will need to install SDK version 21.

<br/>
Make sure that you have JDK 7 installed before building. You can get it [Here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html). Once you have that installed, navigate to File > Project Structure > SDK Location and change the path of your current JDK to the path of the new JDK. **The project will not build with JDK 8**. 

<br/>
Once it is finished installing, you will need to enable developer options on your phone. To do so,
go into settings, About Phone, locate your Build Number, and tap it 7 times, or until it says
"You are now a Developer". Then, go back to the main Settings screen and scroll once again to the
bottom. Select Developer options and enable USB Debugging.

<br/>
Then plug your phone into your computer and hit the large green play button at the top of
Android Studio. It will load for a moment before prompting you to select which device to install
it on. Select your device from the list, and hit continue.

