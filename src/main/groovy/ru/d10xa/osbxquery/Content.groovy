package ru.d10xa.osbxquery
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'content')
class Content {

    String content

    Content(String content) {
        this.content = content
    }

    String toString(){
        content
    }

}
