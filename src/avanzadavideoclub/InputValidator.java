package avanzadavideoclub;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vyyk on 05/06/2015.
 */
public class InputValidator {
    public static boolean textIsLatinButNoPunctuation(String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean textIsNumericOnly(String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean textIsDecimalOnly(String text, String precisionPostDecimal){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    /*
    public static boolean validateIncomingTextForCantidadMateriales(String text, int textLength, boolean isDecimal,
                                                                    double curDouble){
        return curDouble < 10000 && isDecimal && (textLength < 8) || text.isEmpty();
    }

    public static boolean validateIncomingTextForColorTextField(String text, int textLength, boolean isTextOnly){
        return isTextOnly && (textLength < 30)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForDescripcionTextField(String text, int textLength, boolean isTextOnly){
        return isTextOnly && (textLength < 60)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForDescuentoMateriales(String text, int textLength, boolean isDecimal,
                                                                     double curDouble){
        return curDouble <= 1 && isDecimal && (textLength < 5) || text.isEmpty();
    }

    public static boolean validateIncomingTextForExistenciasTextField(String text, int textLength,
                                                                      boolean textIsNumericOnly){
        return textIsNumericOnly && (textLength < 7)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForLargoAnchoTextField(String text, int textLength, boolean isDecimal,
                                                                     double curDouble){
        return curDouble < 1000 && isDecimal && (textLength < 6) || text.isEmpty();
    }

    public static boolean validateIncomingTextForMayoreoMenudeoTextField(String text, int textLength, boolean isDecimal,
                                                                         double curDouble){
        return curDouble < 100000 && isDecimal && (textLength < 9) || text.isEmpty();
    }

    public static boolean validateIncomingTextForTallaTextField(String text, int textLength, boolean isDecimal,
                                                                double curDouble){
        return curDouble < 100 && isDecimal && (textLength < 5) || text.isEmpty();
    }

    public static boolean validateIfUserPressedBackSpaceForSearchProducts(String oldVal, String newVal){
        return oldVal != null && (newVal.length() < oldVal.length());
    }

    public static boolean validateIfEntryTextContainsUserInput(String entryText, String part){
        return entryText.toUpperCase().contains(part);
    }

    public static boolean validateIfMaterialIsSelectedAndHasCantidadSeleccionada(Material curMaterial){
        return curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() > 0.0;
    }

    public static boolean validateIfMaterialIsSelectedButHasNoCantidadSeleccionada(Material curMaterial){
        return curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() == 0.0;
    }

    public static boolean validateCantidadDeMaterialIsNotEmptyAndIsNotADot(String newValue){
        return !newValue.isEmpty() && !newValue.equals(".");
    }

    public static boolean validateGuardarProducto(String descripcion, String existencias, String descuento,
                                                  String precioMayoreo, String precioMenudeo, boolean serigrafiaIsSelected,
                                                  boolean bordadoIsSelected, List<Material> listaMateriales){
        return (descripcion.length() > 0) && (existencias.length() > 0)
                && (descuento.length() > 0) && (precioMayoreo.length() > 0)
                && (precioMenudeo.length() > 0) && (serigrafiaIsSelected
                || bordadoIsSelected) && (!listaMateriales.isEmpty() || (listaMateriales.size() > 0));
    }

    public static boolean validateGuardarPlayera(boolean guardarProductoOK, String talla, String color,
                                                 boolean tipoFirstChoiceSelected, boolean tipoSecondChoiceSelected){
        return guardarProductoOK && (talla.length() > 0) && (color.length() > 0)
                &&(tipoFirstChoiceSelected || tipoSecondChoiceSelected);
    }

    public static boolean validateGuardarSudaderaGorra(boolean guardarProductoOK, String talla, String color){
        return guardarProductoOK && (talla.length() > 0) && (color.length() > 0);
    }

    public static boolean validateGuardarParche(boolean guardarProductoOK, String largo, String ancho){
        return guardarProductoOK && (largo.length() > 0) && (ancho.length() > 0);
    }
    */
}
