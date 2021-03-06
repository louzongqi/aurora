/*
 * Created on 2008-1-24
 */
package aurora.bm;

import java.io.IOException;

import uncertain.composite.CompositeMap;
import uncertain.composite.DynamicObject;
import uncertain.core.ConfigurationError;
import uncertain.exception.BuiltinExceptionFactory;
import uncertain.exception.ProgrammingException;
import aurora.application.AuroraApplication;
import aurora.service.validation.IParameter;

public class Field extends DynamicObject implements IParameter {
    
    public static final String KEY_IS_CACHE_JOIN_FIELD = "_iscachejoinfield";
    public static final String KEY_RELATION_NAME = "relationname";
    public static final String KEY_SOURCE_MODEL = "sourcemodel";
    public static final String KEY_SOURCE_FIELD = "sourcefield";
    public static final String KEY_EXPRESSION = "expression";
    public static final String KEY_NAME = "name";
    public static final String KEY_PARAMETER_PATH = "parameterpath";
    public static final String KEY_DATA_TYPE = "datatype";    
    public static final String KEY_DATABASE_TYPE = "databasetype";
    public static final String KEY_PHYSICAL_NAME = "physicalname";
    public static final String KEY_UPDATE_EXPRESSION = "updateexpression";
    public static final String KEY_INSERT_EXPRESSION = "insertexpression";    
    public static final String KEY_QUERY_EXPRESSION = "queryexpression";
    public static final String KEY_IS_PRIMARYKEY = "isprimarykey";
    public static final String KEY_REQUIRED = "required";
    public static final String KEY_DEFAULT_VALUE = "defaultvalue"; 
    public static final String KEY_FOR_SELECT = "forselect";
    public static final String KEY_FOR_QUERY = "forquery";
    public static final String KEY_FOR_INSERT = "forinsert";    
    public static final String KEY_FOR_UPDATE = "forupdate";
    public static final String KEY_FORCE_UPDATE = "forceupdate";
    public static final String KEY_FOR_DISPLAY = "fordisplay";
    public static final String REF_FIELD = "ref-field";
    public static final String KEY_PROMPT = "prompt";
    public static final String KEY_QUERY_WIDTH = "querywidth";
    public static final String KEY_DISPLAY_WIDTH = "displaywidth";
    public static final String KEY_DISPLAY_ALIGN = "displayalign";
    public static final String KEY_EDITOR_TYPE = "editortype";
    public static final String KEY_OPTIONS = "options";
    public static final String KEY_LOOKUP_FIELD = "lookupfield";
    public static final String KEY_LOOKUP_CODE = "lookupcode";
    public static final String KEY_IS_AUTO_GENERATED_KEY = "isautogeneratedkey";
    
    BusinessModel       owner;
    public static final String KEY_MULTI_LANGUAGE="multilanguage";
    public static final String KEY_MULTI_LANGUAGE_DESC_FIELD="multilanguagedescfield";
        
    public void setMultiLanguage(boolean multiLanguage){
    	putBoolean(KEY_MULTI_LANGUAGE, multiLanguage);
    }
    public boolean getMultiLanguage(){
    	return getBoolean(KEY_MULTI_LANGUAGE,false);
    }
    public void setMultiLanguageDescField(String multiLanguageDescField){
    	putString(KEY_MULTI_LANGUAGE_DESC_FIELD, multiLanguageDescField);
    }
    public String getMultiLanguageDescField(){
    	return getString(KEY_MULTI_LANGUAGE_DESC_FIELD);
    }
    public static Field getInstance(CompositeMap context){
        Field field = new Field();
        field.initialize(context);
        return field;
    }
    
    public static Field createField( String name ){
        CompositeMap m = new CompositeMap();
        m.setName("field");
        m.setNameSpaceURI(AuroraApplication.AURORA_BUSINESS_MODEL_NAMESPACE);
        Field field = new Field();
        field.initialize(m);
        field.setName(name);
        return field;
    }
    
    public static String defaultParamExpression( String name ){
        return "${" + name + "}";
    }
    
    public String getName(){
        return getString(KEY_NAME);
    }
    
    public void setName(String name){
        putString(KEY_NAME, name);
    }
    
    public String getInputPath(){
        String path = getString(KEY_PARAMETER_PATH);
        if(path!=null) 
            return path;
        else
            return getString(KEY_PARAMETER_PATH, "@"+getName() );
    }
    
    public void setParameterPath( String path ){
        putString( KEY_PARAMETER_PATH, path );
    }
    
    public String getParameterPath(){
        return getString(KEY_PARAMETER_PATH, "@"+getName() );
    }
    
    public String getPrompt(){
        return getString(KEY_PROMPT);
    }
    
    public void setPrompt(String prompt){
        putString(KEY_PROMPT, prompt);
    }
    
    public String getDataType(){
        return getString(KEY_DATA_TYPE);
    }
    
    public void setDataType(String data_type){
        putString(KEY_DATA_TYPE, data_type);
    }
    
    public String getDatabaseType(){
        return getString(KEY_DATABASE_TYPE);
    }
    
    public void setDatabaseType(String database_type){
        putString(KEY_DATABASE_TYPE, database_type);
    }
    
    public String getPhysicalName(){
        String name = getString(KEY_PHYSICAL_NAME);
        if(name==null) name = getName();
        return name;
    }
    
    public void setPhysicalName( String name ){
        putString(KEY_PHYSICAL_NAME, name);
    }
    
    /*
    public Model getParent(){
        return Model.getInstance(getObjectContext().getParent());
    }
    */
    
    public boolean isReferenceField(){
        return REF_FIELD.equals(getObjectContext().getName());
    }
    public void setReferenceField(boolean is_ref_field){
    	if(is_ref_field)
    		getObjectContext().setName(REF_FIELD);
    	else
    		getObjectContext().setName("field");
    }
    
    public boolean isExpression(){
        return getObjectContext().containsKey(KEY_EXPRESSION);
    }
    
    public String getExpression(){
        return getString(KEY_EXPRESSION);
    }
    
    public void setExpression(String exp){
        putString(KEY_EXPRESSION, exp);
    }
    
    public String getSourceField(){
        return getString(KEY_SOURCE_FIELD);
    }
    
    public void setSourceField(String field){
        putString(KEY_SOURCE_FIELD, field);
    }
    
    public String getSourceModel(){
        return getString(KEY_SOURCE_MODEL);
    }
    
    public void setSourceModel(String model_name){
        putString(KEY_SOURCE_MODEL, model_name);
    }
    
    public String getRelationName(){
        return getString(KEY_RELATION_NAME);
    }
    
    public String getUpdateExpression(){
        String exp = getString(KEY_UPDATE_EXPRESSION);
        if(exp==null) exp = defaultParamExpression(getInputPath());
        return exp;
    }
    
    public void setUpdateExpression(String source){
        putString(KEY_UPDATE_EXPRESSION, source);
    }

    public String getInsertExpression(){
        String exp = getString(KEY_INSERT_EXPRESSION);
        if(exp==null) exp = defaultParamExpression(getInputPath());
        return exp;
    }
    
    public void setInsertExpression(String source){
        putString(KEY_INSERT_EXPRESSION, source);
    }
    
    public String getQueryExpression(){
        return getString(KEY_QUERY_EXPRESSION);
    }
    
    public void setQueryExpression(String source){
        putString(KEY_QUERY_EXPRESSION, source);
    }    
    
    public void setRelationName(String name){
        putString(KEY_RELATION_NAME, name);
    }
    
    public boolean isPrimaryKey(){
        return getBoolean(KEY_IS_PRIMARYKEY,false);
    }
    
    public void setPrimaryKey( boolean is_pk ){
        putBoolean(KEY_IS_PRIMARYKEY, is_pk);
    }
    
    public int getQueryWidth(){
    	return getInt(KEY_QUERY_WIDTH, 150);
    }
    public void setQueryWidth(int width){
    	putInt(KEY_QUERY_WIDTH, width);
    }
    
    public int getDisplayWidth(){
    	return getInt(KEY_DISPLAY_WIDTH, 150);
    }
    public void setDisplayWidth(int width){
    	putInt(KEY_DISPLAY_WIDTH, width);
    }
    
    public String getDisplayAlign(){
    	return getString(KEY_DISPLAY_ALIGN,"left");    	
    }
    public void setDisplayAlign(String align){
    	putString(KEY_DISPLAY_ALIGN, align);
    }
    
    public String getOptions(){
    	return getString(KEY_OPTIONS,"");
    }
    public void setOptions(String options){
    	putString(KEY_OPTIONS,options);
    }
    
    public String getEditorType(){
    	return getString(KEY_EDITOR_TYPE,"");    	
    }
    
    public void setEditorType(String editorType){
    	putString(KEY_EDITOR_TYPE, editorType);
    }
    
    public boolean isDateType() {
    	String type = getDataType();
    	return "date".equals(type) || "java.util.Date".equals(type) || "java.sql.Date".equals(type);
    }
    
    public boolean isForInsert(){
        if( isReferenceField() )
            return false;
        return getBoolean(KEY_FOR_INSERT, true);
    }
    
    public boolean isForUpdate(){
        Boolean b = getBoolean(KEY_FOR_UPDATE);
        if(b==null){
            return !isPrimaryKey() && !isExpression() && !isReferenceField();
        }
        else
            return b.booleanValue();
    }
    
    public boolean isForQuery(){
        Boolean b = getBoolean(KEY_FOR_QUERY);
        if(b==null){
            return false;
        }
        else
            return b.booleanValue();
    }
    
    public boolean isForDisplay(){
        Boolean b = getBoolean(KEY_FOR_DISPLAY);
        if(b==null)
            return false;
        else
            return b.booleanValue();
    }
    public void setForDisplay(boolean b){
    	putBoolean(KEY_FOR_DISPLAY, b);
    }
    
    /** default true */
    public boolean isForSelect(){
        Boolean b = getBoolean(KEY_FOR_SELECT);
        if(b==null)
            return true;
        else
            return b.booleanValue();
    }
    
    public boolean isForOperation( String operation ){
        return isForOperation( operation, false );
    }
    
    public boolean isForOperation( String operation, boolean default_value ){
        if(operation==null) throw new IllegalArgumentException("operation name is null");
        String key = "for" + operation.toLowerCase();
        Boolean b = getBoolean(key);
        return b==null?default_value:b.booleanValue();
    }
    
    public boolean getRequired(){
        return getBoolean(KEY_REQUIRED, false);
    }
    
    public void setRequired(boolean required){
        putBoolean(KEY_REQUIRED, required);
    }
    
    public boolean isRequired(){
        return getRequired();
    }
    
    public Object getDefaultValue(){
        return get(KEY_DEFAULT_VALUE);
    }
    
    public void setDefaultValue( Object value ){
        put(KEY_DEFAULT_VALUE, value);
    }
    
    public void setLookUpField(String field){
    	putString(KEY_LOOKUP_FIELD,field);
    }
    
    public String getLookUpField(){
    	return getString(KEY_LOOKUP_FIELD);
    }
    
    public void setLookUpCode(String code){
    	putString(KEY_LOOKUP_CODE,code);
    }
    
    public String getLookUpCode(){
    	return getString(KEY_LOOKUP_CODE);
    }
    
    
    public String getReferredModelName(){
        if(!isReferenceField())
            throw new ProgrammingException("this field is not a reference field");
        if(owner==null) throw new ProgrammingException("BusinessModel that owns this field is not set");
        String model = getSourceModel();
        if(model==null){            
            String name = this.getRelationName();
            if(name==null) 
                //throw new IllegalStateException("Must set 'sourceModel' or 'relationName' for this referrence field");
                throw BuiltinExceptionFactory.createOneAttributeMissing(object_context.asLocatable(), "sourceModel,relationName");
            Relation r = owner.getRelation(name);
            if(r==null) 
                //throw new IllegalStateException("Can't find relation "+name);
                throw BuiltinExceptionFactory.createUnknownNodeWithName(object_context.asLocatable(), "relation", "name", name);
            model = r.getReferenceModel();
            if(model==null) 
                //throw new IllegalStateException("'referenceModel' is not set for relation "+r.getObjectContext().toXML());
                throw BuiltinExceptionFactory.createAttributeMissing(r.getObjectContext().asLocatable(), "referenceModel");
        }
        return model;
    }
    
    public BusinessModel getRefferedModel()
    {
        ModelFactory factory = owner.getModelFactory();
        if(factory==null)
            throw new ProgrammingException("ModelFactory instance is not set for BusinessModel");
        String name = getReferredModelName();
        try{
            return factory.getModelForRead(name);
        }catch(IOException ex){
            throw new RuntimeException("Can't load BusinessModel "+name, ex);
        }        
    }
    
    public Field getReferredField()
    {
        String sf = getSourceField();
        if(sf==null) 
            //throw new ConfigurationError("'sourceField' is not set for this referrence field");
            throw BuiltinExceptionFactory.createAttributeMissing(object_context.asLocatable(), "sourceField");
        BusinessModel model = getRefferedModel();        
        Field f = model.getField(sf);
        if(f==null) 
            //throw new IllegalStateException("Can't find referred field '"+sf+"' in referred model");
            throw BuiltinExceptionFactory.createUnknownNodeWithName(object_context.asLocatable(), "field", "name", sf);
        return f;
    }

    /**
     * @return the owner
     */
    public BusinessModel getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(BusinessModel owner) {
        this.owner = owner;
    }
    
    public void setForSelect( boolean b ){
        putBoolean(KEY_FOR_SELECT, b);
    }    
    
    public void setForInsert( boolean b ){
        putBoolean(KEY_FOR_INSERT, b);
    }
    
    public void setForUpdate( boolean b){
        putBoolean(KEY_FOR_UPDATE, b);
    }
    
    public void setForQuery( boolean b){
        putBoolean(KEY_FOR_QUERY, b);
    }
    
    public Field createCopy(){
        CompositeMap m = (CompositeMap)object_context.clone();
        return getInstance(m);
    }
    
    public boolean isForceUpdate(){
        return getBoolean(KEY_FORCE_UPDATE, false);
    }
    
    public void setForceUpdate( boolean b){
        putBoolean(KEY_FORCE_UPDATE, b);
    }
    
    public void checkValidation() {
        if(isReferenceField()){
            getReferredModelName();
            getReferredField();
        }
    }
    
    public boolean isCacheJoinField(){
        return getBoolean(KEY_IS_CACHE_JOIN_FIELD, false);
    }
    
    public void setCacheJoinField(boolean b){
        putBoolean(KEY_IS_CACHE_JOIN_FIELD,b);
    }

    public boolean isAutoGeneratedKey(){
        return getBoolean(KEY_IS_AUTO_GENERATED_KEY, false);
    }
    
    public void setIsAutoGeneratedKey( boolean b ){
        putBoolean(KEY_IS_AUTO_GENERATED_KEY, b);
    }
    

}
