# CustomDIalog

# Шаг 1
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
# Шаг 2
```
  dependencies {
  
	        implementation 'com.github.wefyns:CustomDIalog:1.001'
		
	}
```
  
# Внешний вид
![alt text](https://github.com/wefyns/CustomDIalog/blob/master/Screenshot.png)

# Видео - Инструкция
[![Video](http://img.youtube.com/vi/JhqyPUTSmdw/0.jpg)](http://www.youtube.com/watch?v=JhqyPUTSmdw "Видео")
  
# Как использовать (Kotlin)?
```kotlin
  val lay : RelativeLayout = context.findViewById(R.id.Workout_Main)                      //Лайаут, где должен быть выведен диалог<br/>
  val dialogView = LayoutInflater.from(context).inflate(R.layout.YOUR_VIEW, lay, false)   //XML файл самого диалога<br/>
  val dialog = new CustomDialog(context, dialogView, lay);                                //Создание диалога<br/>
  
  > Тут вы можете инициализировать любой элемент диалога, например:
  val text : TextView = dialog.view.findViewById(R.id.Element_Id).apply{text = "Привет, я диалог"}
  
  > Тут вы можете прописывать разные свойства диалога, перед тем как его показывать, например:
  dialog.setMargins(16, 16)   //Поставить отступы по бокам в 16dp
  
  dialog.show(); 
  
  > Чтобы закрыть из диалог
  dialog.exit()
```

# Функции
  - setMargins(dp, dp) - Поставить отступы по бокам (dp)
  - setBackgroundBlackout(float) - Затемнение перед диалогом(тень)
  - setCanceble() - Если вы нажали за границу диалога, он закроется
  - setParentEnabled(bollean, layout) - Если вы не используете затемнение, то можно выключить родительский лайаут

  - setCancebleBackButton() - Закрывает диалог при нажатии андройдовской кнопки "назад", как использовать:
  > DOCUMENTATION OF THIS METHOD: https://docs.google.com/document/d/1hREJEilSP6vGttuI4sse6m8rGFQfqPOlYjlvIS_XkHM/edit?usp=sharing

  - show() - Показать диалог
  - exit() - Закрыть диалог
