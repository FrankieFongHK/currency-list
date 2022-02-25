# Android-Currency-List
A Exercise to show the currency list

#### Demo
<img height="400px" src="demo/currency.gif" />

#### Project Details:

- MVVM
- Room (Store currency in local database)
- RxJava (Handle multi threading operation & concurrency)
- RxBinding (Throttle multiple click event)
- Dagger2 (Dependency Injection)

#### The app has following packages:
- **data**: It contains all the data accessing and manipulating components
- **di**: Dependency providing classes using Dagger2
- **ui**: View class along with their corresponding ViewModel
- **utils**: Provides common utility methods

## Issue
#### Handle multi threading operation
By using RxJava, queries from local db and sorting the currency list will be processed in a new io thread.

#### Concurrency issue
By using RxBinding, the click event will be observed. With the throttle function, it can prevent the click event be sent twice within the specific seconds.
