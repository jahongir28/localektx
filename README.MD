# LocaleKtx

[![](https://jitpack.io/v/jahongir28/localektx.svg)](https://jitpack.io/#jahongir28/localektx)
[![](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

LocaleKtx is a library to manage your application locale and language.

`Works with "androidx.appcompat:appcompat:1.2.0"`

Once you set a desired locale, LocaleKtx will enforce your application to provide correctly localized data via Resources class.

## Setup

The setup is pretty simple:

1. Using Dependency Injection (Koin Example)

* Create library instance:

    ``` kotlin
    val appModule = module {
        single { createLocaleKtx(context = get()) }
    }
    ```

* Setup Activity (nothing need to do in Fragments)
  
   ``` kotlin
   private val localeKtx: LocaleKtx by inject()

   override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        applyOverrideConfiguration(Configuration())
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {  
        val newConfig = localeKtx.wrapConfiguration(overrideConfiguration)  
        super.applyOverrideConfiguration(newConfig)  
    }
    ```

* Change a locale

    ``` kotlin
       // Top level variable
       private val localeKtx: LocaleKtx by inject()
       
       fun changeLocale(locale: Locale){  
           localeKtx.setLocale(locale = locale)
       }
    ```

2. Using Dependency Injection (Koin Example)

* Initialize the library in Application.onCreate:

    ``` kotlin
    LocaleKtx.init(context)
    ```

* Setup Activity (nothing need to do in Fragments)
  
   ``` kotlin
   override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        applyOverrideConfiguration(Configuration())
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {  
        val localeKtx = LocaleKtx.getInstance()
        val newConfig = localeKtx.wrapConfiguration(overrideConfiguration)  
        super.applyOverrideConfiguration(newConfig)  
    }
    ```

* Change a locale

    ``` kotlin
       fun changeLocale(locale: Locale){  
           LocaleKtx.getInstance().setLocale(locale = locale)
       }
    ```

## Note that you need to recreate Activity after locale changed, there is helper class for that

``` kotlin
//Note: Activity will lose savedInstanceState 
RecreateHelper.recreate(activity)
 ```

## Use in Services

``` kotlin
private val localeKtx: LocaleKtx by inject()

override fun attachBaseContext(base: Context) {
    val newBase = localeKtx.wrapContext(baseContext = base)
    super.attachBaseContext(newBase)
}

```

## Extra

* For keeping county and change only languge

    ``` kotlin
    setLocale(locale = Locale, keepCounty = true)
    ```

## App Bundles

While using an [app bundle](https://developer.android.com/guide/app-bundle), a user’s device only downloads string resources.

To keep languages in build.gradle set:

```gradle
    bundle {
        language {
            enableSplit = false
        }
    }
```

## Download

``` groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation "com.github.jahongir28:localektx:1.0.0"
}
```

## License

```licence
The MIT License (MIT)

Copyright 2021 Jahongir Bekmuhammetov

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
