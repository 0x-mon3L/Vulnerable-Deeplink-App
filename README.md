This is the sample Vulnerable application to perform Deep-Link Attack.

Steps:


Check the following items in AndroidManifest.xml

`android:scheme`
`android:host`
`android:pathPrefix`

ManiActivity.java file has following code which is vulnerable
        
`webSettings.setJavaScriptEnabled(true);`
`webSettings.setAllowUniversalAccessFromFileURLs(true);`

Attack Step:
`adb shell am start -a android.intent.action.VIEW -d 'deeplink://com.deeplink.app/web?url=https://google.com'`
