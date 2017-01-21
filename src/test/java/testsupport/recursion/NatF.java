package testsupport.recursion;

import com.jnape.palatable.lambda.adt.coproduct.CoProduct2;
import com.jnape.palatable.lambda.functor.Functor;

import java.util.function.Function;

public abstract class NatF<A> implements Functor<A, NatF>, CoProduct2<NatF.Z<A>, NatF.S<A>> {

    public static <A> NatF<A> z() {
        return new Z<>();
    }

    public static <A> NatF<A> s(@SuppressWarnings("unused") A a) {
        return new S<>();
    }

    public static final class Z<A> extends NatF<A> {

        private Z() {
        }

        @Override
        @SuppressWarnings("unchecked")
        public <B> NatF<B> fmap(Function<? super A, ? extends B> fn) {
            return (NatF<B>) this;
        }

        @Override
        public <R> R match(Function<? super Z<A>, ? extends R> aFn, Function<? super S<A>, ? extends R> bFn) {
            return aFn.apply(this);
        }
    }

    public static final class S<A> extends NatF<A> {
        private S() {
        }

        @Override
        @SuppressWarnings("unchecked")
        public <B> NatF<B> fmap(Function<? super A, ? extends B> fn) {
            return (NatF<B>) this;
        }

        @Override
        public <R> R match(Function<? super Z<A>, ? extends R> aFn, Function<? super S<A>, ? extends R> bFn) {
            return bFn.apply(this);
        }
    }
}