## BottomActionSheet

### Android library to create a bottom action sheet

### Usage

#### Adding the module in build.gradle

```
    dependencies {
        compile project(':bottomactionsheet')
    }
```

#### Creating BottomActionSheet object

```
    BottomActionSheet bottomactionsheet = new BottomActionSheet(this);
```

#### Creating an ActionSheetElement


```
    ActionSheetElement actionSheetElement = new ActionSheetElement(this);
```

#### Adding an actionSheetElement in Activity

```
    bottomactionsheet.addActionSheetElement(actionSheetElement)
```

#### Showing ActionSheet

```
    bottomactionsheet.show()
```

### Screencast of the demo

<img src="https://github.com/Anwesh43/BottomActionSheet/blob/master/screencast/bottomactionsheet.gif" alt="screencast of demo" width="300px" height="600px">
