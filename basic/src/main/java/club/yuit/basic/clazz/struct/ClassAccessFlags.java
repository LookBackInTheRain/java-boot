package club.yuit.basic.clazz.struct;

import club.yuit.basic.clazz.AccessFlagsUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: yuit
 * @date: 2023/05/30 20:10
 */
@Getter
@Setter
public class ClassAccessFlags {

   private int value;
   private String hex;

   List<ClassAccessFlag> accessFlags;

   @Override
   public String toString() {
      return AccessFlagsUtils.convertToStrings(this.value);
   }
}
