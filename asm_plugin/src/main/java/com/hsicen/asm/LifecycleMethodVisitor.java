package com.hsicen.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 作者：hsicen  2020/4/20 15:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ASM 编译插装，方法处理工具
 */
class LifecycleMethodVisitor extends MethodVisitor {

    private final String className;
    private final String methodName;

    public LifecycleMethodVisitor(MethodVisitor methodVisitor, String className, String name) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        methodName = name;
    }

    @Override //方法执行前插入字节码到class文件中
    public void visitCode() {
        super.visitCode();
        System.out.println("MethodVisitor visit code =============");

        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(className + "---->" + methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }
}
