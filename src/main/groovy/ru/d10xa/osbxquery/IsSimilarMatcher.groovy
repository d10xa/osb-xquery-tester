package ru.d10xa.osbxquery
import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class IsSimilarMatcher extends TypeSafeMatcher<BaseXQCheck> {

    private BaseXQCheck xqueryCheck

    @Override
    protected boolean matchesSafely(BaseXQCheck item) {
        this.xqueryCheck = item
        return item.similar()
    }

    @Factory
    public static Matcher<IsSimilarMatcher> similar() {
        return new IsSimilarMatcher()
    }

    @Override
    void describeTo(Description description) {
        description.appendText("\n${xqueryCheck?.expectedXml}\n")
    }

    @Override
    protected void describeMismatchSafely(BaseXQCheck item, Description mismatchDescription) {
        mismatchDescription.appendText("\n${xqueryCheck.getGeneratedXml()}\n\n")
        def diffs = xqueryCheck.differences
        for (String d : diffs) {
            mismatchDescription.appendText("$d\n")
        }
    }
}