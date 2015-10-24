package hemoterapia.configuration;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import hemoterapia.domain.LodgingsType;
import hemoterapia.domain.WithLodgings;
import hemoterapia.domain.WithoutLodgings;

public class LodgingsUserType implements UserType {

	public static final int WITH_LODGING_DB_VALUE = 1;
	public static final int WITHOUT_LODGING_DB_VALUE = 2;

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		// CustomerStates are immutable
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// CustomerStates are immutable
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		// CustomerStates are immutable
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		// CustomerStates are stateless, therefore they are clearly immutable
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2, Object owner)
			throws HibernateException, SQLException {
		final int lodgingsValue = rs.getInt(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		LodgingsType lodgingsType = null;
		switch (lodgingsValue) {
		case WITH_LODGING_DB_VALUE:
			lodgingsType = WithLodgings.getInstance();
			break;
		case WITHOUT_LODGING_DB_VALUE:
			lodgingsType = WithoutLodgings.getInstance();
			break;
		default:
			throw new RuntimeException("Unknown Lodgins value [" + lodgingsValue + "]");
		}
		return lodgingsType;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor arg3)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.INTEGER);
		} else {
			if (value.equals(WithLodgings.getInstance())) {
				st.setInt(index, WITH_LODGING_DB_VALUE);
			} else if (value.equals(WithoutLodgings.getInstance())) {
				st.setInt(index, WITHOUT_LODGING_DB_VALUE);
			} else {
				throw new RuntimeException("Unknown CustomerState [" + value + "]");
			}
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		// CustomerStates are immutable
		return original;
	}

	@Override
	public Class returnedClass() {
		return LodgingsType.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}
}