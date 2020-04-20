package com.hsicen.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 作者：hsicen  2020/4/20 15:04
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ASM 编译插装，类处理工具
 */
public class LifecycleClassVisitor extends ClassVisitor {

    private String className;
    private String superName;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        className = name;
        this.superName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("ClassVisitor visitMethod name: " + className + "  superName：" + superName);
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);

        if (superName.equals("android/support/v7/app/AppCompatActivity")) {
            if (name.startsWith("onCreate")) {
                return new LifecycleMethodVisitor(methodVisitor, className, name);
            }
        }

        return methodVisitor;
    }
}
