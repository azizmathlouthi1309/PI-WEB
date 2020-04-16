<?php

namespace ActivityBundle\Controller;

use ActivityBundle\Entity\TimeTable;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;

/**
 * Timetable controller.
 *
 * @Route("timetable")
 */
class TimeTableController extends Controller
{
    /**
     * Lists all timeTable entities.
     *
     * @Route("/", name="timetable_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $timeTables = $em->getRepository('ActivityBundle:TimeTable')->findAll();

        return $this->render('timetable/index.html.twig', array(
            'timeTables' => $timeTables,
        ));
    }

    /**
     * Creates a new timeTable entity.
     *
     * @Route("/new", name="timetable_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $timeTable = new Timetable();
        $form = $this->createForm('ActivityBundle\Form\TimeTableType', $timeTable);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($timeTable);
            $em->flush();

            return $this->redirectToRoute('timetable_show', array('id' => $timeTable->getId()));
        }

        return $this->render('timetable/new.html.twig', array(
            'timeTable' => $timeTable,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a timeTable entity.
     *
     * @Route("/{id}", name="timetable_show")
     * @Method("GET")
     */
    public function showAction(TimeTable $timeTable)
    {
        $deleteForm = $this->createDeleteForm($timeTable);

        return $this->render('timetable/show.html.twig', array(
            'timeTable' => $timeTable,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing timeTable entity.
     *
     * @Route("/{id}/edit", name="timetable_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, TimeTable $timeTable)
    {
        $deleteForm = $this->createDeleteForm($timeTable);
        $editForm = $this->createForm('ActivityBundle\Form\TimeTableType', $timeTable);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('timetable_edit', array('id' => $timeTable->getId()));
        }

        return $this->render('timetable/edit.html.twig', array(
            'timeTable' => $timeTable,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a timeTable entity.
     *
     * @Route("/{id}", name="timetable_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, TimeTable $timeTable)
    {
        $form = $this->createDeleteForm($timeTable);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($timeTable);
            $em->flush();
        }

        return $this->redirectToRoute('timetable_index');
    }

    /**
     * Creates a form to delete a timeTable entity.
     *
     * @param TimeTable $timeTable The timeTable entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(TimeTable $timeTable)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('timetable_delete', array('id' => $timeTable->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

}
