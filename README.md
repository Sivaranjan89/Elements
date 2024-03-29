# Elements
UI Components made easy to develop.
Use this library to design a UI element without having to code much.
Available UI Elements with Latest release are,
1) UIButton
2) UIEditText
3) UILabel(TextView)
4) UICheckbox
5) UIRadioButton
6) UIRatingBar
7) UIDrawingBoard
8) UIExpandableListview

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
<b>implementation 'com.github.Sivaranjan89:Elements:3.1'</b><br />
}<br />


# Sample Screenshot
![zzz](https://user-images.githubusercontent.com/54542325/64889848-e5759f80-d68b-11e9-96a9-ce5147c2ab1b.png)


# UIButton Attributes
<ol>
            <li>text</li>
            <li>textSize</li>
            <li>textColor</li>
            <li>textStyle</li>
            <li>fontName</li>
            <li>strokeColor</li>
            <li>strokeWidth</li>
            <li>cornerRadius</li>
            <li>iconShape</li>
            <li>iconWidth</li>
            <li>iconHeight</li>
            <li>spacing</li>
            <li>gravity</li>
            <li>buttonShape</li>
</ol>


# UILabel Attributes
<ol>
            <li>text</li>
            <li>textSize</li>
            <li>textColor</li>
            <li>textStyle</li>
            <li>fontName</li>
            <li>strokeColor</li>
            <li>strokeWidth</li>
            <li>cornerRadius</li>
            <li>backgroundColor</li>
            <li>icon</li>
            <li>iconShape</li>
            <li>iconWidth</li>
            <li>iconHeight</li>
            <li>spacing</li>
            <li>ellipsize</li>
            <li>allCaps</li>
            <li>showDivider</li>
            <li>dividerColor</li>
            <li>dividerWidth</li>
            <li>showLine</li>
            <li>lineColor</li>
            <li>lineWidth</li>
            <li>iconAlignment</li>
            <li>strikeText</li>
            <li>underlineText</li>
            <li>textGravity</li>
            <li>endSpace</li>
            <li>labelPadding</li>
</ol>

### To Span Text in UILabel, use new TextSpanner().build. Several span options are provided. Refer to the example in HomeActivity.java

# UIEditText Attributes
<ol>
            <li>backgroundColor</li>
            <li>text</li>
            <li>textSize</li>
            <li>textColor</li>
            <li>textStyle</li>
            <li>fontName</li>
            <li>strokeColor</li>
            <li>strokeWidth</li>
            <li>cornerRadius</li>
            <li>iconShape</li>
            <li>iconWidth</li>
            <li>iconHeight</li>
            <li>spacing</li>
            <li>hintText</li>
            <li>hintTextColor</li>
            <li>hintTextSize</li>
            <li>helperText</li>
            <li>helperTextSize</li>
            <li>helperTextColor</li>
            <li>helperTextStyle</li>
            <li>helperTextPosition</li>
            <li>helperFontName</li>
            <li>iconAlignment</li>
            <li>editTextShape</li>
            <li>showDivider</li>
            <li>dividerColor</li>
            <li>dividerWidth</li>
            <li>showLine</li>
            <li>lineColor</li>
            <li>lineWidth</li>
            <li>autoStretch</li>
            <li>singleLine</li>
            <li>maxLines</li>
            <li>inputType</li>
            <li>digits</li>
            <li>allowPaste</li>
            <li>allCaps</li>
            <li>maxLength</li>
            <li>letterSpacing</li>
            <li>lines</li>
            <li>lineSpacing</li>
            <li>imeActionLabel</li>
            <li>cursorVisible</li>
            <li>imeOptions</li>
            <li>ellipsize</li>
            <li>currencySymbol</li>
            <li>leftPadding</li>
            <li>topPadding</li>
            <li>bottomPadding</li>
            <li>rightPadding</li>
            <li>disableComponent</li>
            <li>isEditable</li>
</ol>

# UICheckbox Attributes
<ol>
            <li>textSize</li>
            <li>textColor</li>
            <li>textStyle</li>
            <li>fontName</li>
            <li>strokeColor</li>
            <li>strokeWidth</li>
            <li>cornerRadius</li>
            <li>backgroundColor</li>
            <li>checkColor</li>
            <li>customCheckbox</li>
            <li>direction</li>
            <li>renderDirection</li>
            <li>data</li>
</ol>

# UIRadioButton Attributes
<ol>
            <li>textSize</li>
            <li>textColor</li>
            <li>textStyle</li>
            <li>fontName</li>
            <li>strokeColor</li>
            <li>strokeWidth</li>
            <li>cornerRadius</li>
            <li>backgroundColor</li>
            <li>checkColor</li>
            <li>customCheckbox</li>
            <li>direction</li>
            <li>renderDirection</li>
            <li>data</li>
            <li>selectedItem</li>
</ol>

# UIRatingBar Attributes
<ol>
            <li>starCount</li>
            <li>starColor</li>
            <li>filledStar</li>
            <li>emptyStar</li>
            <li>starSize</li>
            <li>spacing</li>
            <li>direction</li>
            <li>progress</li>
</ol>

# UIDrawingBoard Attributes
<ol>
            <li>brushSize</li>
            <li>sketchColor</li>
</ol>

# UIExpandableListView Attributes
<ol>
            <li>singleGroupDisplay</li>
</ol>

### Set data for the expandable listview as shown below, String refers to the parent and object refers to child which can be any array object
expandableListView.setData(HashMap<String, Object>);

### You don't have to create an adapter for expandable listview. Just call as shown below,
expandableListView.designParentChildView(new UIExpandableListView.DesignParentChildView() { <br>
            @Override<br>
            public View designChildView(int parentPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, Object child) {<br>
                return convertView;<br>
            }<br>
            @Override<br>
            public View designParentView(int parentPosition, boolean isExpanded, View convertView, ViewGroup parent, List<String> parentTitles) {<br>
                return convertView;<br>
            }<br>
        });
