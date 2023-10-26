package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static framework.FrameworkUtils.message;

public class AppControl {
    By by;
    String using;

    /* Constructor */
    public AppControl(By by) {
        this.by = by;
        this.using = "";
    }

    public AppControl(String using) {
        this.using = using;
    }
    //region Methods

    public WebElement returnElement(){
        WebElement el = null;
        try {
            if (!using.isEmpty()) {
                el = App.driver.findElementByAccessibilityId(this.using);
            } else {
                el = App.driver.findElement(this.by);
            }
        } catch (NoSuchElementException ex) {
            message("Unable to find element with following locator " + ((!using.isEmpty()) ? this.using : this.by));
        }
        return el;
    }

    public WebElement waitForElementClickable(){
        WebDriverWait wait = new WebDriverWait(App.driver, 300);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(returnElement()));

        return el;
    }
    public Boolean waitForElementText(String text){
        WebDriverWait wait = new WebDriverWait(App.driver, 300);
        boolean res = wait.until(ExpectedConditions.attributeToBe(returnElement(), "text", text));

        return res;
    }

    /**
     * Click this element. If this causes a new page to load, you
     * should discard all references to this element and any further
     * operations performed on this element will throw a
     * StaleElementReferenceException.
     *
     * Note that if click() is done by sending a native event (which is
     * the default on most browsers/platforms) then the method will
     * _not_ wait for the next page to load and the caller should verify
     * that themselves.

     *
     * There are some preconditions for an element to be clicked. The
     * element must be visible and it must have a height and width
     * greater then 0.
     *
     * @throws StaleElementReferenceException If the element no
     *     longer exists as initially defined
     */
    public void click() {
        try {
            returnElement().click();
        } catch (NullPointerException ex){
            message("Unable to find element with following locator " + ((!using.isEmpty()) ? this.using : this.by));
        }
    }

    /**
     * If this current element is a form, or an element within a form, then this will be submitted to
     * the remote server. If this causes the current page to change, then this method will block until
     * the new page is loaded.
     *
     * @throws NoSuchElementException If the given element is not within a form
     */
    public void submit() {
        returnElement().submit();
    }

    /**
     * Use this method to simulate typing into an element, which may set its value.
     */
    public void sendKeys(CharSequence... keysToSend) { returnElement().sendKeys(keysToSend); }
    /**
     * If this element is a text entry element, this will clear the value. Has no effect on other
     * elements. Text entry elements are INPUT and TEXTAREA elements.
     *
     * Note that the events fired by this event may not be as you'd expect.  In particular, we don't
     * fire any keyboard or mouse events.  If you want to ensure keyboard events are fired, consider
     * using something like {@link #sendKeys(CharSequence...)} with the backspace key.  To ensure
     * you get a change event, consider following with a call to {@link #sendKeys(CharSequence...)}
     * with the tab key.
     */
    public void clear() {
        returnElement().clear();
    }

    /**
     * Get the tag name of this element. <b>Not</b> the value of the name attribute: will return
     * <code>"input"</code> for the element <code>&lt;input name="foo" /&gt;</code>.
     *
     * @return The tag name of this element.
     */
    public String getTagName() {
        return returnElement().getTagName();
    }

    /**
     * Get the value of a the given attribute of the element. Will return the current value, even if
     * this has been modified after the page has been loaded. More exactly, this method will return
     * the value of the given attribute, unless that attribute is not present, in which case the value
     * of the property with the same name is returned (for example for the "value" property of a
     * textarea element). If neither value is set, null is returned. The "style" attribute is
     * converted as best can be to a text representation with a trailing semi-colon. The following are
     * deemed to be "boolean" attributes, and will return either "true" or null:
     *
     * async, autofocus, autoplay, checked, compact, complete, controls, declare, defaultchecked,
     * defaultselected, defer, disabled, draggable, ended, formnovalidate, hidden, indeterminate,
     * iscontenteditable, ismap, itemscope, loop, multiple, muted, nohref, noresize, noshade,
     * novalidate, nowrap, open, paused, pubdate, readonly, required, reversed, scoped, seamless,
     * seeking, selected, spellcheck, truespeed, willvalidate
     *
     * Finally, the following commonly mis-capitalized attribute/property names are evaluated as
     * expected:
     *
     * <ul>
     * <li>"class"
     * <li>"readonly"
     * </ul>
     *
     * @param name The name of the attribute.
     * @return The attribute/property's current value or null if the value is not set.
     */
    public String getAttribute(String name) {
        return returnElement().getAttribute(name);
    }

    /**
     * Determine whether or not this element is selected or not. This operation only applies to input
     * elements such as checkboxes, options in a select and radio buttons.
     *
     * @return True if the element is currently selected or checked, false otherwise.
     */
    public boolean isSelected() {
        return returnElement().isSelected();
    }

    /**
     * Is the element currently enabled or not? This will generally return true for everything but
     * disabled input elements.
     *
     * @return True if the element is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return returnElement().isEnabled();
    }

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element, including sub-elements,
     * without any leading or trailing whitespace.
     *
     * @return The innerText of this element.
     */
    public String getText() {
        try {
            return returnElement().getText();
        } catch (NullPointerException ex){
            return "Element not found";
        }
    }

    /**
     * Find all elements within the current context using the given mechanism. When using xpath be
     * aware that webdriver follows standard conventions: a search prefixed with "//" will search the
     * entire document, not just the children of this current node. Use ".//" to limit your search to
     * the children of this WebElement.
     * This method is affected by the 'implicit wait' times in force at the time of execution. When
     * implicitly waiting, this method will return as soon as there are more than 0 items in the
     * found collection, or will return an empty list if the timeout is reached.
     *
     * @param by The locating mechanism to use
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches.
     * @see By
     * @see WebDriver.Timeouts
     */
    public List<WebElement> findElements(By by) {
        return returnElement().findElements(by);
    }

    /**
     * Find the first {@link WebElement} using the given method. See the note in
     * {@link #findElements(By)} about finding via XPath.
     * This method is affected by the 'implicit wait' times in force at the time of execution.
     * The findElement(..) invocation will return a matching row, or try again repeatedly until
     * the configured timeout is reached.
     *
     * findElement should not be used to look for non-present elements, use {@link #findElements(By)}
     * and assert zero length response instead.
     *
     * @param by The locating mechanism
     * @return The first matching element on the current context.
     * @throws NoSuchElementException If no matching elements are found
     * @see By
     * @see WebDriver.Timeouts
     */
    public WebElement findElement(By by) {
        return returnElement().findElement(by);
    }

    /**
     * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     *
     * @return Whether or not the element is displayed
     */
    public boolean isDisplayed() {
        boolean res = false;
        try {
            res = returnElement().isDisplayed();
        } catch (Exception ex) {
            message("Something happens wrong during checking visibility of element");
        }
        return res;
    }

    /**
     * Where on the page is the top left-hand corner of the rendered element?
     *
     * @return A point, containing the location of the top left-hand corner of the element
     */
    public Point getLocation() {
        return returnElement().getLocation();
    }

    /**
     * What is the width and height of the rendered element?
     *
     * @return The size of the element on the page.
     */
    public Dimension getSize() {
        return returnElement().getSize();
    }

    /**
     * Get the value of a given CSS property.
     * Color values should be returned as rgba strings, so,
     * for example if the "background-color" property is set as "green" in the
     * HTML source, the returned value will be "rgba(0, 255, 0, 1)".
     *
     * Note that shorthand CSS properties (e.g. background, font, border, border-top, margin,
     * margin-top, padding, padding-top, list-style, outline, pause, cue) are not returned,
     * in accordance with thethe
     * <a href="http://www.w3.org/TR/DOM-Level-2-Style/css.html#CSS-CSSStyleDeclaration">DOM CSS2 specification</a>
     * - you should directly access the longhand properties (e.g. background-color) to access the
     * desired values.
     *
     * @return The current, computed value of the property.
     */
    public String getCssValue(String propertyName) {
        return returnElement().getCssValue(propertyName);
    }

    public Rectangle getRect() {
        return returnElement().getRect();
    }

    //endregion
}
