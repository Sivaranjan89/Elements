# Elements
UI Components made easy to develop.
Use this library to design a UI element without having to code much.
Available UI Elements with Latest release are,
1) Button
2) EditText
3) TextView

# How to Install Plugin
Add the below in your root build.gradle(project) at the end of repositories:<br />
allprojects {<br />
repositories {<br />
...<br />
<b>maven { url 'https://jitpack.io' }</b><br />
}<br />
}<br />
            
Add the dependency in build.gradle(module) : <br />
dependencies {<br />
<b>implementation 'com.github.Sivaranjan89:Elements:1.1'</b><br />
}<br />


## UIButton
![Screenshot_20190827-194224__01](https://user-images.githubusercontent.com/54542325/63779787-c42f5800-c904-11e9-84ae-7c3822b38986.jpg)

## Example
<com.droid.elements.UIButton <br />
            android:layout_width="50dp" <br />
            android:layout_height="50dp" <br />
            android:layout_gravity="center" <br />
            app:gravity="center" <br />
            app:text="Button Text" <br />
            app:textSize="20dp" <br />
            app:textStyle="bold" <br />
            app:textColor="#000000" <br />
            app:pressedTextColor="#FFFFFF" <br />
            app:backgroundColor="#00C4FF" <br />
            app:pressedBackgroundColor="#000000" <br />
            app:cornerRadius="4dp" <br />
            app:strokeColor="#000000" <br />
            app:strokeWidth="1dp" <br />
            app:icon="@drawable/twiiter" <br />
            app:pressedIcon="@mipmap/ic_launcher" <br />
            app:imageHeight="50dp" <br />
            app:imageWidth="50dp" <br />
            app:iconPosition="left" <br />
            app:buttonShape="oval" <br />
            app:imageShape="normal" <br />
            app:spacing="5dp" <br />
            app:fontName="calypto.ttf" /> <br />

## Attributes
app:gravity="center" -> Set Gravity for Icon and Text <br />
app:text="Button Text" -> Set Text <br />
app:textSize="20dp" -> Set Text Size <br />
app:textStyle="bold" -> Set Text Style <br />
app:textColor="#000000" -> Set Text Color <br />
app:pressedTextColor="#FFFFFF" -> Set Text Color When Pressed <br />
app:backgroundColor="#00C4FF" -> Set Background Color <br />
app:pressedBackgroundColor="#000000" -> Set Background Color When Pressed <br />
app:cornerRadius="4dp" -> Set Corner Radius <br />
app:strokeColor="#000000" -> Set Button Outline Color <br />
app:strokeWidth="1dp" -> Set Outline Size <br />
app:icon="@drawable/twiiter" -> Set Button Icon <br />
app:pressedIcon="@mipmap/ic_launcher" -> Set Button Icon when pressed <br />
app:imageHeight="50dp" -> Set Icon height <br />
app:imageWidth="50dp" -> Set Icon Width <br />
app:iconPosition="left" -> Set Position of the Icon Relative to Text <br />
app:buttonShape="oval" -> Set Button Shape <br />
app:imageShape="normal" -> Set Image Shape <br />
app:spacing="5dp" -> Set Spacing between image and text <br />
app:fontName="calypto.ttf" -> Set font path in assets <br />



## UIEditText
![Screenshot_20190827-231855__01](https://user-images.githubusercontent.com/54542325/63796294-6eb67380-c923-11e9-8209-51c8bfc4d141.jpg)

## Example
<com.droid.elements.UIEditText <br />
            android:layout_width="200dp" <br />
            android:layout_height="wrap_content" <br />
            android:layout_marginTop="10dp" <br />
            android:layout_gravity="center" <br />
            app:hintText="Icon EditText" <br />
            app:cornerRadius="5dp" <br />
            app:strokeColor="#000000" <br />
            app:strokeWidth="1dp" <br />
            app:icon="@drawable/twiiter" <br />
            app:imageWidth="30dp" <br />
            app:imageHeight="30dp" <br />
            app:showDivider="true" <br />
            app:dividerColor="#000000" <br />
            app:editTextShape="rectangle" <br />
            app:imeOptions="next"/> <br />

## Attributes
app:backgroundColor="#00C4FF" -> Set Background Color <br />
app:text="Button Text" -> Set Text <br />
app:textSize="20dp" -> Set Text Size <br />
app:textStyle="bold" -> Set Text Style <br />
app:textColor="#000000" -> Set Text Color <br />
app:cornerRadius="4dp" -> Set Corner Radius <br />
app:strokeColor="#000000" -> Set Button Outline Color <br />
app:strokeWidth="1dp" -> Set Outline Size <br />
app:icon="@drawable/twiiter" -> Set Button Icon <br />
app:imageShape="normal" -> Set Image Shape <br />
app:imageHeight="50dp" -> Set Icon height <br />
app:imageWidth="50dp" -> Set Icon Width <br />
app:spacing="5dp" -> Set Spacing between image and text <br />
app:iconAlignment="left" -> Set Position of the Icon Relative to Text <br />
app:hintTextColor="#000000" -> Set Color for Hint Text <br />
app:helperTextColor="#000000" -> Set Color for Helper Text <br />
app:hintText="Hint Text" -> Set Hint<br />
app:helperText="Hint Text" -> Set Helper Text<br />
app:helperTextSize="20dp" -> Set Text Size for Helper Text <br />
app:helperTextColor="#000000" -> Set Text Color for Helper Text <br />
app:helperTextPosition="bottom_left" -> Set Helper Text Position<br />
app:editTextShape="rectangle" -> Set EditText Shape <br />
app:showDivider="true" -> Displays the divider between image and text <br />
app:dividerColor="#000000" -> Set Color for Divider <br />
app:dividerWidth="1dp" -> Set Width for Divider <br />
app:showLine="true" -> Displays the line below edittext (Note : This will only work if edittext shape is none <br />
app:lineColor="#000000" -> Set Color for Line <br />
app:lineWidth="1dp" -> Set Width for Line <br />
app:autoStretch="true" -> The edittext will stretch as the input characters keep increasing <br />
app:currencySymbol="1dp" -> Will auto append the currency when user types any character <br />
app:animate="true" -> Animates the edittext on start <br />
app:animDuration="1000" -> Duration for the animation <br />
app:leftPadding="10dp" -> set Left Padding for EditText <br />
app:rightPadding="10dp" -> set Right Padding for EditText <br />
app:topPadding="10dp" -> set Top Padding for EditText <br />
app:bottomPadding="10dp" -> set Bottom Padding for EditText <br />



# UILabel (TextView)
![uilabel](https://user-images.githubusercontent.com/54542325/63907177-13c27080-ca38-11e9-958d-0cf61faeb595.png)
