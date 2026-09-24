This is a collection of desktop web tests for Playwright.<br>
This works with the Perfecto brand, a product of Perforce.<br>

For details of this product integration, please see the official help documentation from Perfecto.<br>

https://help.perfecto.io/perfecto-help/content/perfecto/automation-testing/playwright-integrate-w-perfecto-web.htm

How This Works: <br>
This sample uses a security token to access the cloud.<br>
Under the myUtilities folder is a logins.java file.<br>
Update your cloud short name and the security token in the logins.java file for this parameter.<br>
```
Map.entry("testing", "abcd1234")
```

These are the strings that control the test details. <br>
Set the host value to your cloud short name. <br>
	private static String host = "testing";
  
We need to set the browserVersion to one we currently support. <br>
```
private static String browserVersion = "150";
```

We need to set the browserLocation to one of the data centers. <br>
Valid Locations: (US East), (EU Frankfurt),  (AP Sydney).  <br>
These are North America, Germany and Australia respectively. <br>
```
private static String browserLocation = "EU Frankfurt";
```
