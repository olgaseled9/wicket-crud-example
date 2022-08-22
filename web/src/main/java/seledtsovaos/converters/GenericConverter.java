package seledtsovaos.converters;


public interface GenericConverter<I, O> {

    I convertToDto(O entity);

    O convertBack(I entity);
}

