<?php

namespace RestoNurseryBundle\Controller;

use AppBundle\Entity\User;
use FOS\UserBundle\FOSUserBundle;
use RestoNurseryBundle\Entity\SaveResto;
use AppBundle\Form\SearchRestoType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;
use Symfony\Component\Validator\Constraints\Email;

/**
 * Saveresto controller.
 *
 * @Route("saveresto")
 */
class SaveRestoController extends Controller
{
    /**
     * Lists all saveResto entities.
     *
     * @Route("/", name="saveresto_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $saveRestos = $em->getRepository('RestoNurseryBundle:SaveResto')->findAll();

        return $this->render('saveresto/index.html.twig', array(
            'saveRestos' => $saveRestos,
        ));
    }

    /**
     * Lists all saveResto entities.
     *
     * @Route("/", name="saveresto_indexF")
     * @Method("GET")
     */
    public function indexFAction()
    {
        $em = $this->getDoctrine()->getManager();

        $saveRestos = $em->getRepository('RestoNurseryBundle:SaveResto')->findAll();

        return $this->render('saveresto/indexF.html.twig', array(
            'saveRestos' => $saveRestos,
        ));
    }

    /**
     * Creates a new saveResto entity.
     *
     * @Route("/new", name="saveresto_new")
     * @Method({"GET"})
     */
    public function newAction(Request $request)
    {
        //$user=$this->getUser();
        //$this->getDoctrine()->getRepository(User::class)->find($user);
        $saveResto = new Saveresto();
        $form = $this->createForm('RestoNurseryBundle\Form\SaveRestoType', $saveResto);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($saveResto);
            $em->flush();

            return $this->redirectToRoute('saveresto_show', array('id' => $saveResto->getId()));
        }

        $mail = new PHPMailer(true);

        try {
            //Server settings
            //$mail->SMTPDebug = 1;                      // Enable verbose debug output
            $mail->isSMTP();                                            // Send using SMTP
            $mail->Host       = 'smtp.gmail.com';                    // Set the SMTP server to send through
            $mail->SMTPAuth   = true;                                   // Enable SMTP authentication
            // SMTP password
            $mail->SMTPSecure = 'tls';         // Enable TLS encryption; `PHPMailer::ENCRYPTION_SMTPS` also accepted
            $mail->Port       = 587;                                    // TCP port to connect to
            /* Username (email address). */
            $mail->Username = 'kindogarten2020@gmail.com';

            /* Google account password. */
            $mail->Password = '2020kindo';
            //Recipients
            $mail->setFrom('from@example.com', 'Subscription SUCCESSFUL');
            $mail->addAddress('nada.chniter@esprit.tn');     // Add a recipient

            /* // Attachments
             $mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
             $mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name*/

            // Content
            $mail->isHTML(true);                                  // Set email format to HTML
            $mail->Subject = 'Subscription Accepted';
            $mail->Body    = ' Your child s subscription has been added successfuly <b>!</b> ';
            $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

            $mail->send();
          //  echo 'Message has been sent';
        } catch (Exception $e) {
           // echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
        }
        return $this->render('saveresto/new.html.twig', array(
            'saveResto' => $saveResto,
            'form' => $form->createView(),
        ));

    }

    /**
     * Finds and displays a saveResto entity.
     *
     * @Route("/{id}", name="saveresto_show")
     * @Method("GET")
     */
    public function showAction(SaveResto $saveResto)
    {
        $deleteForm = $this->createDeleteForm($saveResto);

        return $this->render('saveresto/show.html.twig', array(
            'saveResto' => $saveResto,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Finds and displays a saveResto entity.
     *
     * @Route("/{id}", name="saveresto_showB")
     * @Method("GET")
     */
    public function showBAction(SaveResto $saveResto)
    {
        $deleteForm = $this->createDeleteForm($saveResto);

        return $this->render('saveresto/showB.html.twig', array(
            'saveResto' => $saveResto,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing saveResto entity.
     *
     * @Route("/{id}/edit", name="saveresto_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, SaveResto $saveResto)
    {
        $deleteForm = $this->createDeleteForm($saveResto);
        $editForm = $this->createForm('RestoNurseryBundle\Form\SaveRestoType', $saveResto);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('saveresto_edit', array('id' => $saveResto->getId()));
        }

        return $this->render('saveresto/edit.html.twig', array(
            'saveResto' => $saveResto,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing saveResto entity.
     *
     * @Route("/{id}/edit", name="saveresto_editF")
     * @Method({"GET", "POST"})
     */
    public function editFAction(Request $request, SaveResto $saveResto)
    {
        $deleteForm = $this->createDeleteForm($saveResto);
        $editForm = $this->createForm('RestoNurseryBundle\Form\SaveRestoType', $saveResto);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('saveresto_edit', array('id' => $saveResto->getId()));
        }

        return $this->render('saveresto/editF.html.twig', array(
            'saveResto' => $saveResto,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a saveResto entity.
     *
     * @Route("/{id}", name="saveresto_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, SaveResto $saveResto)
    {
        $form = $this->createDeleteForm($saveResto);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($saveResto);
            $em->flush();
        }

        return $this->redirectToRoute('saveresto_indexF');
    }

    /**
     * Creates a form to delete a saveResto entity.
     *
     * @param SaveResto $saveResto The saveResto entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    public function createDeleteForm(SaveResto $saveResto)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('saveresto_delete', array('id' => $saveResto->getId())))
            ->setMethod('DELETE')
            ->getForm();
    }

    /**
     * @Route("/preInfosResto", name="preInfosResto")
     */
    public function searchaction(Request $request)
    {
        $searchform = $this->createForm('RestoNurseryBundle\Form\SearchRestoType');

        if ($searchform->isValid() && $searchform->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $idChild = $request->get('idc');
            $saveresto = $em->getRepository('RestoNurseryBundle:SaveResto')->findBy(array('idc' => $idChild));
            $this->redirectToRoute('saveresto_show', array('id' => $saveresto->getId()));

        }
        return $this->render('saveresto/preInfosResto.html.twig', array(
            'search_form' => $searchform->createView(),
        ));
    }

    }


