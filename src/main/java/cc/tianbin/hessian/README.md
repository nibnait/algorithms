
# 序列化

writeObject

```java
  Hessian2Output

  /**
   * Writes any object to the output stream.
   */
  @Override
  public void writeObject(Object object)
    throws IOException
  {
    if (object == null) {
      writeNull();
      return;
    }

    Serializer serializer
      = findSerializerFactory().getObjectSerializer(object.getClass());

    serializer.writeObject(object, this);
  }

    UnsafeSerializer.writeObject

  final public void writeInstance(Object obj, AbstractHessianOutput out)
    throws IOException
  {
    try {
      FieldSerializer []fieldSerializers = _fieldSerializers;
      int length = fieldSerializers.length;
      
      for (int i = 0; i < length; i++) {
        fieldSerializers[i].serialize(out, obj);
      }
    } catch (RuntimeException e) {
      throw new RuntimeException(e.getMessage() + "\n class: "
                                 + obj.getClass().getName()
                                 + " (object=" + obj + ")",
                                 e);
    } catch (IOException e) {
      throw new IOExceptionWrapper(e.getMessage() + "\n class: "
                                   + obj.getClass().getName()
                                   + " (object=" + obj + ")",
                                   e);
    }
  }
```


# 反序列化
readObject

```java
  private Object readObjectInstance(Class<?> cl,
                                    ObjectDefinition def)
    throws IOException
  {
    String type = def.getType();
    Deserializer reader = def.getReader();
    Object []fields = def.getFields();

    SerializerFactory factory = findSerializerFactory();
    
    if (cl != reader.getType() && cl != null) {
      reader = factory.getObjectDeserializer(type, cl);
      
      return reader.readObject(this, def.getFieldNames());
    }
    else {
      return reader.readObject(this, fields);
    }
  }


    UnsafeSerializer.readObject

  protected HashMap<String,FieldDeserializer2> 
  getFieldMap(Class<?> cl, FieldDeserializer2Factory fieldFactory)
  {
    HashMap<String,FieldDeserializer2> fieldMap
      = new HashMap<String,FieldDeserializer2>();
    
    for (; cl != null; cl = cl.getSuperclass()) {
      Field []fields = cl.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
        Field field = fields[i];

        if (Modifier.isTransient(field.getModifiers())
            || Modifier.isStatic(field.getModifiers()))
          continue;
        else if (fieldMap.get(field.getName()) != null)
          continue;

        /*
        // XXX: could parameterize the handler to only deal with public
        try {
          field.setAccessible(true);
        } catch (Throwable e) {
          e.printStackTrace();
        }
        */

        FieldDeserializer2 deser = fieldFactory.create(field);

        fieldMap.put(field.getName(), deser);
      }
    }

    return fieldMap;
  }

```