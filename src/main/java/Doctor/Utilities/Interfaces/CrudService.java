/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities.Interfaces;

/**
 *
 * @author Chahir Chalouati
 * @param <A>
 * @param <B>
 * @param <C>
 */
public interface CrudService<Payload, ID, Response> {

    Response create(Payload payload);

    Response delete(ID id);

    Response update(Payload payload, ID id);
}
