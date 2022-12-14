package org.codehaus.stax2;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.stax2.typed.TypedXMLStreamWriter;
import org.codehaus.stax2.validation.Validatable;

/**
 * Extended interface that implements functionality that is necessary
 * to properly build event API on top of {@link XMLStreamWriter},
 * as well as to configure individual instances.
 * It also adds limited number of methods that are important for
 * efficient pass-through processing (such as one needed when routing
 * SOAP-messages).
 *<p>
 * Since version 3.0, stream writer will also implement "Typed Access API"
 * on output side.
 *
 * @version 3.0.1 06-Nov-2008
 * @author Tatu Saloranta (tatu.saloranta@iki.fi)
 */
public interface XMLStreamWriter2
    extends TypedXMLStreamWriter,
            Validatable
{
    /*
    ///////////////////////////////////////////////////////////
    // Configuration
    ///////////////////////////////////////////////////////////
    */

    /**
     * Method similar to {@link javax.xml.stream.XMLOutputFactory#isPropertySupported}, used
     * to determine whether a property is supported by the Writer
     * <b>instance</b>. This means that this method may return false
     * for some properties that the output factory does support: specifically,
     * it should only return true if the value is mutable on per-instance
     * basis. False means that either the property is not recognized, or
     * is not mutable via writer instance.
     */
    public boolean isPropertySupported(String name);

    /**
     * Method that can be used to set per-writer properties; a subset of
     * properties one can set via matching
     * {@link org.codehaus.stax2.XMLOutputFactory2}
     * instance. Exactly which methods are mutable is implementation
     * specific.
     *
     * @param name Name of the property to set
     * @param value Value to set property to.
     *
     * @return True, if the specified property was <b>succesfully</b>
     *    set to specified value; false if its value was not changed
     *
     * @throws IllegalArgumentException if the property is not supported
     *   (or recognized) by the stream writer implementation
     */
    public boolean setProperty(String name, Object value);

    /*
    ///////////////////////////////////////////////////////////
    // Other accessors, mutators
    ///////////////////////////////////////////////////////////
    */

    /**
     * Method that should return current output location, if the writer
     * keeps track of it; null if it does not.
     */
    public XMLStreamLocation2 getLocation();

    /**
     * Method that can be called to get information about encoding that
     * this writer is using (or at least claims is using). That is,
     * it returns name of encoding specified when (in order of priority):
     *<ul>
     * <li>Passed to one of factory methods of
     *    {@link javax.xml.stream.XMLOutputFactory}
     *  </li>
     * <li>Passed to <code>writeStartDocument</code> method (explicitly
     *   or implicity; latter in cases where defaults are imposed
     *   by Stax specification)
     *  </li>
     * </ul>
     */
    public String getEncoding();

    /*
    ///////////////////////////////////////////////////////////
    // Write methods base interface is missing
    ///////////////////////////////////////////////////////////
    */

    public void writeCData(char[] text, int start, int len)
        throws XMLStreamException;

    public void writeDTD(String rootName, String systemId, String publicId,
                         String internalSubset)
        throws XMLStreamException;

    /**
     * Method similar to {@link #writeEndElement}, but that will always
     * write the full end element, instead of empty element. This only
     * matters for cases where the element itself has no content, and
     * if writer is allowed to write empty elements when it encounters
     * such start/end element write pairs.
     */
    public void writeFullEndElement() throws XMLStreamException;

    public void writeStartDocument(String version, String encoding,
                                   boolean standAlone)
        throws XMLStreamException;

    /**
     * Method that can be called to write whitespace-only content.
     * If so, it is to be written as is (with no escaping), and does
     * not contain non-whitespace characters (writer may validate this,
     * and throw an exception if it does).
     *<p>
     * This method is useful for things like outputting indentation.
     *
     * @since 3.0
     */
    public void writeSpace(String text)
        throws XMLStreamException;

    /**
     * Method that can be called to write whitespace-only content.
     * If so, it is to be written as is (with no escaping), and does
     * not contain non-whitespace characters (writer may validate this,
     * and throw an exception if it does).
     *<p>
     * This method is useful for things like outputting indentation.
     *
     * @since 3.0
     */
    public void writeSpace(char[] text, int offset, int length)
        throws XMLStreamException;
    
    /*
    ///////////////////////////////////////////////////////////
    // Pass-through methods
    ///////////////////////////////////////////////////////////
    */

    /**
     * Method that writes specified content as is, without encoding or
     * deciphering it in any way. It will not update state of the writer
     * (except by possibly flushing output of previous writes, like
     * finishing a start element),
     * nor be validated in any way. As such, care must be taken, if this
     * method is used.
     *<p>
     * Method is usually used when encapsulating output from another writer
     * as a sub-tree, or when passing through XML fragments.
     *<p>
     * NOTE: since text to be written may be anything, including markup,
     * it can not be reliably validated. Because of this, validator(s)
     * attached to the writer will NOT be informed about writes.
     */
    public void writeRaw(String text)
        throws XMLStreamException;

    /**
     * Method that writes specified content as is, without encoding or
     * deciphering it in any way. It will not update state of the writer
     * (except by possibly flushing output of previous writes, like
     * finishing a start element),
     * nor be validated in any way. As such, care must be taken, if this
     * method is used.
     *<p>
     * Method is usually used when encapsulating output from another writer
     * as a sub-tree, or when passing through XML fragments.
     *<p>
     * NOTE: since text to be written may be anything, including markup,
     * it can not be reliably validated. Because of this, validator(s)
     * attached to the writer will NOT be informed about writes.
     */
    public void writeRaw(String text, int offset, int length)
        throws XMLStreamException;

    /**
     * Method that writes specified content as is, without encoding or
     * deciphering it in any way. It will not update state of the writer
     * (except by possibly flushing output of previous writes, like
     * finishing a start element),
     * nor be validated in any way. As such, care must be taken, if this
     * method is used.
     *<p>
     * Method is usually used when encapsulating output from another writer
     * as a sub-tree, or when passing through XML fragments.
     *<p>
     * NOTE: since text to be written may be anything, including markup,
     * it can not be reliably validated. Because of this, validator(s)
     * attached to the writer will NOT be informed about writes.
     */
    public void writeRaw(char[] text, int offset, int length)
        throws XMLStreamException;

    /**
     * Method that essentially copies
     * event that the specified reader has just read.
     * This can be both more convenient
     * (no need to worry about details) and more efficient
     * than separately calling access methods of the reader and
     * write methods of the writer, since writer may know more
     * about reader than the application (and may be able to use
     * non-public methods)
     *
     * @param r Reader to use for accessing event to copy
     * @param preserveEventData If true, writer is not allowed to change
     *   the state of the reader (so that all the data associated with the
     *   current event has to be preserved); if false, writer is allowed
     *   to use methods that may cause some data to be discarded. Setting
     *   this to false may improve the performance, since it may allow
     *   full no-copy streaming of data, especially textual contents.
     */
    public void copyEventFromReader(XMLStreamReader2 r, boolean preserveEventData)
        throws XMLStreamException;

    /*
    ///////////////////////////////////////////////////////////
    // Output handling
    ///////////////////////////////////////////////////////////
    */

    /**
     * Method similar to
     * {@link javax.xml.stream.XMLStreamWriter#close()},
     * except that this method also does close the underlying output
     * destination (stream) if it has not yet been closed.
     * It is specifically necessary to call this method if the parsing ends
     * in an exception to ensure that the output destination does get
     * properly closed, even if the stream writer would otherwise close
     * it (as is the case for destinations it manages where calling
     * application has no access)
     */
    public void closeCompletely()
        throws XMLStreamException;
}
