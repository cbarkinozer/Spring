# Refleciton 

A feature that provides the opportunity to examine and direct the runtime behavior of applications running in the JVM.  
It is used to obtain, control and manage information such as names and parameters of classes, methods, properties and annotations.  
For the use of Reflection, it will be useful to know the Class, Method, Field, Annotation classes and methods in the java.lang package.  
The Class class has methods such as getName, getSimpleName, getModifiers, getPackage, getSuperclass, getInterfaces, getConstructors, getMethods, getFields, getAnnotations to get information about the class.  
When it is desired to run a method with a reflection structure, the invoke() method in the Method class can be used.  
With the Reflection structure, it is also possible to access non-accessible areas such as private and protected in the class.  
The most important use of the Java Reflection structure is the annotations structure because structures such as Spring, Hibernate, and JAXB (XML operations) operate using reflection and annotations.  

The following method prints the annotations in the class given as a parameter:  
```java
public static void yazdir(Class<?> annotationClass) {
    for (Annotation annotation : annotationClass.getAnnotations()) {
        System.out.println(annotation.toString());
    }
}
```

By using the reflection structure, operations such as dynamically creating objects from the class, class loading, dependency management (DI) can be done easily.  
However, unexpected results may occur when the reflection structure is not used appropriately.  


# HATEOAS  
# Transaction  
