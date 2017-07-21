package com.zuicoding.platform.blog.core;

import com.zuicoding.platform.blog.utils.LogUtil;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Stephen.lin on 2017/7/18.
 * <p>
 * Description :<p></p>
 */
public class MenuCache extends Directive {

    private LogUtil log = LogUtil.newLogUtil(MenuCache.class);

    public MenuCache(){
        log.i("=======><=========");
    }

    /**
     * Return the name of this directive.
     *
     * @return The name of this directive.
     */
    @Override
    public String getName() {
        return "menu";
    }

    /**
     * Get the directive type BLOCK/LINE.
     *
     * @return The directive type BLOCK/LINE.
     */
    @Override
    public int getType() {
        return LINE;
    }

    /**
     * How this directive is to be rendered
     *
     * @param context
     * @param writer
     * @param node
     * @return True if the directive rendered successfully.
     * @throws IOException
     * @throws ResourceNotFoundException
     * @throws ParseErrorException
     * @throws MethodInvocationException
     */
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        return false;
    }
}
