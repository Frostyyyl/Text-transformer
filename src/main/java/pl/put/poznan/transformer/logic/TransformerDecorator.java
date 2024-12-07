package pl.put.poznan.transformer.logic;

/**
 * An abstract base class for creating decorators for the {@link Transformer} interface.
 * This class is designed to allow additional functionality to be dynamically
 * added to basic implementations of the {@link Transformer} objects by wrapping them.
 */
public abstract class TransformerDecorator implements Transformer {

    /**
     * The wrapped {@link Transformer} instance whose behavior is being extended or modified.
     */
    protected Transformer transformer;

    /**
     * Constructs a {@code TransformerDecorator} with the specified {@link Transformer} instance.
     *
     * @param transformer the {@link Transformer} instance to be wrapped by this decorator
     */
    public TransformerDecorator(Transformer transformer) {
        this.transformer = transformer;
    }

    /**
     * Processes the input text using the wrapped {@link Transformer}.
     * Subclasses can override this method to add additional behavior to the transformation process.
     *
     * @param text the input text to be transformed
     * @return the transformed text
     */
    @Override
    public String transform(String text) {
        return transformer.transform(text);
    }
}
