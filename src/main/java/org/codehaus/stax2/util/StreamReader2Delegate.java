package org.codehaus.stax2.util;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

import org.codehaus.stax2.AttributeInfo;
import org.codehaus.stax2.DTDInfo;
import org.codehaus.stax2.LocationInfo;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.typed.Base64Variant;
import org.codehaus.stax2.typed.TypedArrayDecoder;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.codehaus.stax2.validation.ValidationProblemHandler;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidator;

public class StreamReader2Delegate
    extends StreamReaderDelegate
    implements XMLStreamReader2
{
    protected XMLStreamReader2 _delegate2;

    /*
    /**********************************************************************
    /* Life-cycle
    /**********************************************************************
     */

    public StreamReader2Delegate(XMLStreamReader2 sr)
    {
        super(sr);
        _delegate2 = sr;
    }

    @Override
    public void setParent(XMLStreamReader pr)
    {
        super.setParent(pr);
        _delegate2 = (XMLStreamReader2) pr;
    }

    /*
    /**********************************************************************
    /* XMLStreamReader2 implementation
    /**********************************************************************
     */

    public void closeCompletely() throws XMLStreamException {
        _delegate2.closeCompletely();
    }

    public AttributeInfo getAttributeInfo() throws XMLStreamException {
        return _delegate2.getAttributeInfo();
    }

    public DTDInfo getDTDInfo() throws XMLStreamException {
        return _delegate2.getDTDInfo();
    }

    public int getDepth() {
        return _delegate2.getDepth();
    }

    @SuppressWarnings("deprecation")
    public Object getFeature(String name) {
        return _delegate2.getFeature(name);
    }

    public LocationInfo getLocationInfo() {
        return _delegate2.getLocationInfo();
    }

    public NamespaceContext getNonTransientNamespaceContext() {
        return _delegate2.getNonTransientNamespaceContext();
    }

    public String getPrefixedName() {
        return _delegate2.getPrefixedName();
    }

    public int getText(Writer w, boolean preserveContents)
        throws IOException, XMLStreamException
    {
        return _delegate2.getText(w, preserveContents);
    }

    public boolean isEmptyElement()
        throws XMLStreamException
    {
        return _delegate2.isEmptyElement();
    }

    public boolean isPropertySupported(String name) {
        return _delegate2.isPropertySupported(name);
    }

    @SuppressWarnings("deprecation")
    public void setFeature(String name, Object value) {
        _delegate2.setFeature(name, value);
    }

    public boolean setProperty(String name, Object value) {
        return _delegate2.setProperty(name, value);
    }

    public void skipElement() throws XMLStreamException {
        _delegate2.skipElement();
    }

    /*
    /**********************************************************************
    /* XMLStreamReader2, Validatable
    /**********************************************************************
     */

    public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
        return _delegate2.setValidationProblemHandler(h);
    }

    public XMLValidator stopValidatingAgainst(XMLValidationSchema schema)
        throws XMLStreamException
    {
        return _delegate2.stopValidatingAgainst(schema);
    }

    public XMLValidator stopValidatingAgainst(XMLValidator validator)
        throws XMLStreamException
    {
        return _delegate2.stopValidatingAgainst(validator);
    }

    public XMLValidator validateAgainst(XMLValidationSchema schema)
        throws XMLStreamException
    {
        return _delegate2.validateAgainst(schema);
    }

    /*
    /**********************************************************************
    /* TypedXMLStreamReader implementation
    /**********************************************************************
     */

    public int getAttributeIndex(String namespaceURI, String localName) {
        return _delegate2.getAttributeIndex(namespaceURI, localName);
    }

    public boolean getAttributeAsBoolean(int index) throws XMLStreamException
    {
        return _delegate2.getAttributeAsBoolean(index);
    }

    public BigDecimal getAttributeAsDecimal(int index)
        throws XMLStreamException
    {
        return _delegate2.getAttributeAsDecimal(index);
    }

    public double getAttributeAsDouble(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsDouble(index);
    }

    public float getAttributeAsFloat(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsFloat(index);
    }

    public int getAttributeAsInt(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsInt(index);
    }

    public BigInteger getAttributeAsInteger(int index)
        throws XMLStreamException
    {
        return _delegate2.getAttributeAsInteger(index);
    }

    public long getAttributeAsLong(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsLong(index);
    }

    public QName getAttributeAsQName(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsQName(index);
    }

    public int[] getAttributeAsIntArray(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsIntArray(index);
    }

    public long[] getAttributeAsLongArray(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsLongArray(index);
    }

    public float[] getAttributeAsFloatArray(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsFloatArray(index);
    }

    public double[] getAttributeAsDoubleArray(int index) throws XMLStreamException {
        return _delegate2.getAttributeAsDoubleArray(index);
    }

    public void getElementAs(TypedValueDecoder tvd) throws XMLStreamException {
        _delegate2.getElementAs(tvd);
    }

    public boolean getElementAsBoolean() throws XMLStreamException {
        return _delegate2.getElementAsBoolean();
    }

    public BigDecimal getElementAsDecimal() throws XMLStreamException {
        return _delegate2.getElementAsDecimal();
    }

    public double getElementAsDouble() throws XMLStreamException {
        return _delegate2.getElementAsDouble();
    }

    public float getElementAsFloat() throws XMLStreamException {
        return _delegate2.getElementAsFloat();
    }

    public int getElementAsInt() throws XMLStreamException {
        return _delegate2.getElementAsInt();
    }

    public BigInteger getElementAsInteger() throws XMLStreamException {
        return _delegate2.getElementAsInteger();
    }

    public long getElementAsLong() throws XMLStreamException {
        return _delegate2.getElementAsLong();
    }

    public QName getElementAsQName() throws XMLStreamException {
        return _delegate2.getElementAsQName();
    }

    public byte[] getElementAsBinary() throws XMLStreamException {
        return _delegate2.getElementAsBinary();
    }

    public byte[] getElementAsBinary(Base64Variant v) throws XMLStreamException {
        return _delegate2.getElementAsBinary(v);
    }

    public void getAttributeAs(int index, TypedValueDecoder tvd)
        throws XMLStreamException
    {
        _delegate2.getAttributeAs(index, tvd);
    }

    public int getAttributeAsArray(int index, TypedArrayDecoder tad)
        throws XMLStreamException
    {
        return _delegate2.getAttributeAsArray(index, tad);
    }

    public byte[] getAttributeAsBinary(int index) throws XMLStreamException
    {
        return _delegate2.getAttributeAsBinary(index);
    }

    public byte[] getAttributeAsBinary(int index, Base64Variant v) throws XMLStreamException
    {
        return _delegate2.getAttributeAsBinary(index, v);
    }

    public int readElementAsDoubleArray(double[] value, int from, int length)
        throws XMLStreamException
    {
        return _delegate2.readElementAsDoubleArray(value, from, length);
    }

    public int readElementAsFloatArray(float[] value, int from, int length)
        throws XMLStreamException
    {
        return _delegate2.readElementAsFloatArray(value, from, length);
    }

    public int readElementAsIntArray(int[] value, int from, int length)
        throws XMLStreamException {
        return _delegate2.readElementAsIntArray(value, from, length);
    }

    public int readElementAsLongArray(long[] value, int from, int length)
        throws XMLStreamException
    {
        return _delegate2.readElementAsLongArray(value, from, length);
    }

    public int readElementAsArray(TypedArrayDecoder tad)
        throws XMLStreamException
    {
        return _delegate2.readElementAsArray(tad);
    }

    public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength)
        throws XMLStreamException
    {
        return _delegate2.readElementAsBinary(resultBuffer, offset, maxLength);
    }

    public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength,
                                   Base64Variant v)
        throws XMLStreamException
    {
        return _delegate2.readElementAsBinary(resultBuffer, offset, maxLength, v);
    }
}
