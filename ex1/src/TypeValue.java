/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author manish
 */
class TypeValue {
    private Object value;
    private Type type;
    
    public TypeValue(){}

    public TypeValue(int value)
    {
        type = Type.integer();
        this.value = (Object) value;
    }
    
    public TypeValue(float value)
    {
        type = Type.floating();
        this.value = (Object) value;
    }

    public TypeValue (boolean mutex)
    {
        type = Type.bool();
        this.value = (Object) mutex;
    }

    public Type getType()
    {
        return type;
    }

    public Object getValue()
    {
        return value;
    }
    
}
